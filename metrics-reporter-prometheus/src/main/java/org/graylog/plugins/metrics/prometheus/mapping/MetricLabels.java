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
