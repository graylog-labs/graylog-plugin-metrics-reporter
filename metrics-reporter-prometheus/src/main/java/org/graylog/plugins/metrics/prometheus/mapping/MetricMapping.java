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