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
package org.graylog.plugins.metrics.prometheus;

import com.github.joschi.jadconfig.Parameter;
import com.github.joschi.jadconfig.util.Duration;
import com.github.joschi.jadconfig.validators.PositiveDurationValidator;
import com.google.common.net.HostAndPort;
import org.graylog.plugins.metrics.core.jadconfig.StringMapConverter;
import org.graylog2.plugin.PluginConfigBean;

import java.util.Collections;
import java.util.Map;

public class MetricsPrometheusReporterConfiguration implements PluginConfigBean {
    private static final String PREFIX = "metrics_prometheus_";

    @Parameter(PREFIX + "enabled")
    private boolean enabled = false;

    @Parameter(value = PREFIX + "report_interval", required = true, validator = PositiveDurationValidator.class)
    private Duration reportInterval = Duration.seconds(15L);

    @Parameter(value = PREFIX + "address", required = true)
    private HostAndPort address = HostAndPort.fromParts("127.0.0.1", 9091);

    @Parameter(value = PREFIX + "job_name", required = true)
    private String jobName = "graylog";

    @Parameter(value = PREFIX + "grouping_key", converter = StringMapConverter.class)
    private Map<String, String> groupingKey = Collections.emptyMap();

    public boolean isEnabled() {
        return enabled;
    }

    public Duration getReportInterval() {
        return reportInterval;
    }

    public HostAndPort getAddress() {
        return address;
    }

    public String getJobName() {
        return jobName;
    }

    public Map<String, String> getGroupingKey() {
        return groupingKey;
    }
}
