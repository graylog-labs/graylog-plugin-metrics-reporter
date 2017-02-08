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
package org.graylog.plugins.metrics.prometheus.mapping;

import io.prometheus.client.Collector.MetricFamilySamples;
import io.prometheus.client.Collector.MetricFamilySamples.Sample;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MetricMapping {

    private final List<MappingConfig> configs;

    public MetricMapping() throws MappingConfigSyntaxException {
        configs = new LinkedList<>();
        configs.add(
                new MappingConfig(
                        "org_graylog2_inputs_gelf_http_GELFHttpInput_rawSize_total",
                        "org.graylog2.inputs.gelf.http.GELFHttpInput.(.*).rawSize.total",
                        new String[] { "hash" }));
        configs.add(
                new MappingConfig(
                        "org_graylog2_plugin_streams_Stream_incomingMessages_1_sec_rate",
                        "org.graylog2.plugin.streams.Stream.(.*).incomingMessages.1.sec.rate",
                        new String[] { "hash" }
                ));
        configs.add(
                new MappingConfig(
                        "org_graylog_inputs_beats_plugin_BeatsInput_emptyMessages",
                        "org.graylog.inputs.beats.plugin.BeatsInput.(.*).emptyMessages",
                        new String[] { "hash" }
                ));
        configs.add(
                new MappingConfig(
                        "org_graylog2_inputs_gelf_http_GELFHttpInput_org_graylog2_inputs_transports_HttpTransport_worker_executor_service_running",
                        "org.graylog2.inputs.gelf.http.GELFHttpInput.(.*).org.graylog2.inputs.transports.HttpTransport.worker.executor.service.running",
                        new String[] { "hash" }
                ));
        configs.add(
                new MappingConfig(
                        "org_graylog2_inputs_codecs_GelfCodec_parseTime",
                        "org.graylog2.inputs.codecs.GelfCodec.(.*).parseTime",
                        new String[] { "hash" }
                ));
        configs.add(
                new MappingConfig(
                        "org_graylog2_inputs_gelf_http_GELFHttpInput_org_graylog2_inputs_transports_HttpTransport_worker_executor_service_duration",
                        "org.graylog2.inputs.gelf.http_GELFHttpInput.(.*).org.graylog2.inputs.transports.HttpTransport.worker.executor.service.duration",
                        new String[] { "hash" }
                ));
        configs.add(
                new MappingConfig(
                        "org_graylog2_plugin_streams_StreamRule_executionTime",
                        "org.graylog2.plugin.streams.StreamRule.(.*).executionTime",
                        new String[] { "hash" }
                ));
        configs.add(
                new MappingConfig(
                        "org_graylog_inputs_beats_plugin_BeatsInput_incomingMessages_total",
                        "org.graylog.inputs.beats.plugin.BeatsInput.(.*).incomingMessages.total",
                        new String[] { "hash" }
                ));
        configs.add(
                new MappingConfig(
                        "org_graylog_inputs_beats_plugin_BeatsInput_rawSize_total",
                        "org.graylog.inputs.beats.plugin.BeatsInput.(.*).rawSize.total",
                        new String[] { "hash" }
                ));
        configs.add(
                new MappingConfig(
                        "org_graylog2_inputs_codecs_GelfCodec_incomplete_total",
                        "org.graylog2.inputs.codecs.GelfCodec.(.*).incomplete.total",
                        new String[] { "hash" }
                ));
        configs.add(
                new MappingConfig(
                        "org_graylog2_inputs_codecs_GelfCodec_processedMessages_total",
                        "org.graylog2.inputs.codecs.GelfCodec.(.*).processedMessages.total",
                        new String[] { "hash" }
                ));
        configs.add(
                new MappingConfig(
                        "org_graylog2_inputs_gelf_http_GELFHttpInput_org_graylog2_inputs_transports_HttpTransport_worker_executor_service_completed_total",
                        "org.graylog2.inputs.gelf.http.GELFHttpInput.(.*).org.graylog2.inputs.transports.HttpTransport_worker_executor_service_completed_total",
                        new String[] { "hash" }
                ));
        configs.add(
                new MappingConfig(
                        "org_graylog2_inputs_gelf_http_GELFHttpInput_inputs_transports_HttpTransport_worker_executor_service_submitted_total",
                        "org.graylog2.inputs.gelf.http.GELFHttpInput.(.*).org.graylog2.inputs.transports.HttpTransport.worker.executor.service.submitted.total",
                        new String[] { "hash" }
                ));
        configs.add(
                new MappingConfig(
                        "org_graylog2_inputs_gelf_http_GELFHttpInput_rawSize_total",
                        "org.graylog2.inputs.gelf.http.GELFHttpInput.(.*).rawSize.total",
                        new String[] { "hash" }
                ));
        configs.add(
                new MappingConfig(
                        "org_graylog2_plugin_streams_Stream_incomingMessages_total",
                        "org.graylog2.plugin.streams.Stream.(.*).incomingMessages.total",
                        new String[] { "hash" }
                ));
    }

    public MetricMapping(final List<MappingConfig> configs) {
        this.configs = configs;
    }

    /**
     * Extract labels and values from all configured mapping rules for the given
     * metric.
     *
     * @param metricName
     *            name of the metric to be mapped
     * @return new name for this metric along with labels and label values
     */
    MetricLabels extractAllLabelValues(final String metricName) {
        for (final MappingConfig mc : configs) {
            final Map<String, String> match = extractLabelsValuesForMetric(
                    metricName, mc.getPattern(), mc.getLabels());
            int size = match.size();
            if (size > 0) {
                return new MetricLabels(mc.getName(), match);
            }
        }
        return null;
    }

    // package-private for testing
    /**
     * Extract labels for given metric name, pattern and labels.
     *
     * @param metricName
     *            name of the metric
     * @param pattern
     *            pattern to use for extraction
     * @param labels
     *            keys for resulting map
     * @return map containing labels as keys and extracted values
     */
    Map<String, String> extractLabelsValuesForMetric(
            final String metricName, final String pattern, String[] labels) {
        final HashMap<String, String> m = new HashMap<>();
        final Pattern p = Pattern.compile(pattern);
        final Matcher matcher = p.matcher(metricName);
        for (int i = 0; matcher.find(); i++) {
            final String group = matcher.group(i + 1);
            m.put(labels[i], group);
        }
        return m;
    }

    /**
     * Wrapper method to produce an enumeration of {@link MetricFamilySamples}
     * for mapped input metrics. All metrics are processed and if a match in the
     * mapping rules is found they are applied. The resulting metrics have
     * additional labels and values based on the mapping configuration used.
     *
     * @param originalMetricFamilySamples
     *            input metrics
     * @return mapped metrics
     */
    public Enumeration<MetricFamilySamples> getUpdatedMetrics(
            final Enumeration<MetricFamilySamples> originalMetricFamilySamples) {
        final List<MetricFamilySamples> updatedMetricFamilySamplesList = new LinkedList<>();
        while (originalMetricFamilySamples.hasMoreElements()) {
            final MetricFamilySamples nextMetricFamilySamples
                    = originalMetricFamilySamples.nextElement();
            final String metricName = nextMetricFamilySamples.name;
            final MetricLabels metricLabels = extractAllLabelValues(metricName);
            if (metricLabels != null) {
                final MetricFamilySamples updatedMetricFamilySamples
                        = getUpdatedMetricFamilySamples(
                                nextMetricFamilySamples,
                                metricLabels.getMetricName(),
                                new LinkedList<>(metricLabels.getLabelKeys()),
                                new LinkedList<>(metricLabels.getLabelValues()));

                updatedMetricFamilySamplesList.add(updatedMetricFamilySamples);
            } else {
                updatedMetricFamilySamplesList.add(nextMetricFamilySamples);
            }
        }
        return Collections.enumeration(updatedMetricFamilySamplesList);
    }

    /**
     * Rename the given {@link MetricFamilySamples} and extend it with
     * additional labels and label values.
     *
     * @param metricFamilySamples
     *            original {@link MetricFamilySamples}
     * @param metricName
     *            new name
     * @param additionalLabels
     *            labels to add
     * @param additionalValues
     *            label values to add
     * @return updated {@link MetricFamilySamples}
     */
    private MetricFamilySamples getUpdatedMetricFamilySamples(
            final MetricFamilySamples metricFamilySamples,
            final String metricName,
            final List<String> additionalLabels,
            final List<String> additionalValues) {
        final List<Sample> updatedSamples = new LinkedList<>();
        for (final Sample sample : metricFamilySamples.samples) {
            final List<String> updatedLabelNames = new LinkedList<>();
            for (final String labelName : sample.labelNames) {
                updatedLabelNames.add(labelName);
            }
            updatedLabelNames.addAll(additionalLabels);
            final List<String> updatedLabelValues = new LinkedList<>();
            for (final String labelValue : sample.labelValues) {
                updatedLabelValues.add(labelValue);
            }
            updatedLabelValues.addAll(additionalValues);
            final Sample updatedSample = new Sample(
                    metricName,
                    updatedLabelNames,
                    updatedLabelValues,
                    sample.value);

            updatedSamples.add(updatedSample);
        }
        final MetricFamilySamples updateMetricFamilySamples = new MetricFamilySamples(
                metricFamilySamples.name,
                metricFamilySamples.type, metricFamilySamples.help, updatedSamples);
        return updateMetricFamilySamples;
    }
}