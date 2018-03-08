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
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;
import io.swagger.annotations.ApiOperation;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Enumeration;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.graylog.plugins.metrics.prometheus.mapping.MappingConfigSyntaxException;
import org.graylog.plugins.metrics.prometheus.mapping.MetricMapping;
import org.graylog2.plugin.rest.PluginRestResource;

import static java.util.Objects.requireNonNull;

@Path("/metrics")
@RequiresAuthentication
public class MetricsResource implements PluginRestResource {
    private final CollectorRegistry collectorRegistry;
    private final MetricMapping metricsMapping;

    @Inject
    public MetricsResource(final CollectorRegistry collectorRegistry)
            throws MappingConfigSyntaxException {
        this.collectorRegistry = requireNonNull(collectorRegistry);
        metricsMapping = new MetricMapping();
    }

    @GET
    @Produces(TextFormat.CONTENT_TYPE_004)
    @ApiOperation("Provide internal Graylog metrics in Prometheus metrics format")
    public Response prometheusMetrics() {
        final StreamingOutput stream = os -> {
            try (final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(os);
                    final Writer writer = new BufferedWriter(outputStreamWriter)) {
                if (metricsMapping == null) {
                    TextFormat.write004(writer, collectorRegistry.metricFamilySamples());
                } else {
                    final Enumeration<MetricFamilySamples> originalMetricFamilySamples
                            = collectorRegistry.metricFamilySamples();
                    final Enumeration<MetricFamilySamples> updatedMetricFamilySamples = metricsMapping
                            .getUpdatedMetrics(originalMetricFamilySamples);
                    TextFormat.write004(writer, updatedMetricFamilySamples);
                }
                writer.flush();
            }
        };
        return Response.ok(stream)
                .type(TextFormat.CONTENT_TYPE_004)
                .build();
    }
}
