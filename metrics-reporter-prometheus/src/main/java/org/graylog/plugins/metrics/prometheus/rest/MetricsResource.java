/**
 * This file is part of Graylog Metrics Prometheus Reporter Plugin.
 *
 * Graylog Metrics Prometheus Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics Prometheus Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics Prometheus Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.prometheus.rest;

import io.prometheus.client.Collector.MetricFamilySamples;
import io.prometheus.client.Collector.MetricFamilySamples.Sample;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;
import io.swagger.annotations.ApiOperation;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.graylog.plugins.metrics.prometheus.mapping.MetricsMapping;
import org.graylog2.plugin.rest.PluginRestResource;

import static java.util.Objects.requireNonNull;

@Path("/metrics")
@RequiresAuthentication
public class MetricsResource implements PluginRestResource {
    private final CollectorRegistry collectorRegistry;
    private final MetricsMapping metricsMapping = new MetricsMapping();

    @Inject
    public MetricsResource(final CollectorRegistry collectorRegistry) {
        this.collectorRegistry = requireNonNull(collectorRegistry);
    }

    @GET
    @Produces(TextFormat.CONTENT_TYPE_004)
    @ApiOperation("Provide internal Graylog metrics in Prometheus metrics format")
    public Response prometheusMetrics() {
        final StreamingOutput stream = os -> {
            try (final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(os);
                    final Writer writer = new BufferedWriter(outputStreamWriter)) {
                final Enumeration<MetricFamilySamples> originalMetricFamilySamples = collectorRegistry
                        .metricFamilySamples();
                final List<MetricFamilySamples> updatedMetricFamilySamplesList = new LinkedList<>();
                while (originalMetricFamilySamples.hasMoreElements()) {
                    final MetricFamilySamples nextElement = originalMetricFamilySamples
                            .nextElement();
                    final String metricName = nextElement.name;
                    final Map<String, String> m = metricsMapping.matches(metricName);
                    if (m.size() > 0) {
                        final MetricFamilySamples updatedMetricFamilySamples
                                = getUpdatedMetricFamilySamples(
                                        nextElement,
                                        new LinkedList<>(m.keySet()),
                                        new LinkedList<>(m.values()));

                        updatedMetricFamilySamplesList.add(updatedMetricFamilySamples);
                    } else {
                        updatedMetricFamilySamplesList.add(nextElement);
                    }
                }
                final Enumeration<MetricFamilySamples> metricFamilySamplesEnumeration = Collections
                        .enumeration(updatedMetricFamilySamplesList);
                TextFormat.write004(writer, metricFamilySamplesEnumeration);
                writer.flush();
            }
        };
        return Response.ok(stream)
                .type(TextFormat.CONTENT_TYPE_004)
                .build();
    }

    private MetricFamilySamples getUpdatedMetricFamilySamples(
            final MetricFamilySamples nextElement,
            final List<String> newLabels,
            final List<String> newValues) {
        final List<Sample> updatedSamples = new LinkedList<>();
        for (final Sample sample : nextElement.samples) {
            final List<String> updatedLabelNames = new LinkedList<>();
            for (final String labelName : sample.labelNames) {
                updatedLabelNames.add(labelName);
            }
            updatedLabelNames.addAll(newLabels);
            final List<String> updatedLabelValues = new LinkedList<>();
            for (final String labelValue : sample.labelValues) {
                updatedLabelValues.add(labelValue);
            }
            updatedLabelValues.addAll(newValues);
            final Sample updatedSample = new Sample(
                    // sample.name,
                    // TODO: parameterise
                    "org_graylog2_inputs_gelf_http_GELFHttpInput_rawSize_total",
                    updatedLabelNames,
                    updatedLabelValues,
                    sample.value);

            updatedSamples.add(updatedSample);
        }
        final MetricFamilySamples m = new MetricFamilySamples(
                nextElement.name,
                //
                nextElement.type,
                nextElement.help,
                updatedSamples);
        return m;
    }
}
