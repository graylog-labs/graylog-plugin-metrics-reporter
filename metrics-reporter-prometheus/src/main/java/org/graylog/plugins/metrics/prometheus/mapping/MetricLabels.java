package org.graylog.plugins.metrics.prometheus.mapping;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Simple wrapper for a metric name plus labels.
 * 
 * @author neumayer
 *
 */
public class MetricLabels {

    private final String metricName;
    private final Map<String, String> labelMap;

    public MetricLabels(final String metricName, final Map<String, String> labelMap) {
        this.metricName = metricName;
        this.labelMap = labelMap;
    }

    public String getMetricName() {
        return metricName;
    }

    public Set<String> getLabelKeys() {
        return labelMap.keySet();
    }

    public Collection<String> getLabelValues() {
        return labelMap.values();
    }

    public Map<String, String> getLabelMap() {
        return labelMap;
    }
}
