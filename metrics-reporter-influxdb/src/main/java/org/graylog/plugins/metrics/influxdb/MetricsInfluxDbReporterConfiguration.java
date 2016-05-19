/**
 * This file is part of Graylog Metrics InfluxDB Reporter Plugin.
 *
 * Graylog Metrics InfluxDB Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics InfluxDB Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics InfluxDB Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.influxdb;

import com.github.joschi.jadconfig.Parameter;
import com.github.joschi.jadconfig.ValidationException;
import com.github.joschi.jadconfig.ValidatorMethod;
import com.github.joschi.jadconfig.util.Duration;
import com.github.joschi.jadconfig.validators.PositiveDurationValidator;
import org.graylog.plugins.metrics.influxdb.jadconfig.StringMapConverter;
import org.graylog2.plugin.PluginConfigBean;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MetricsInfluxDbReporterConfiguration implements PluginConfigBean {
    private static final String PREFIX = "metrics_influxdb_";

    @Parameter(PREFIX + "enabled")
    private boolean enabled = false;

    @Parameter(value = PREFIX + "report_interval", required = true, validator = PositiveDurationValidator.class)
    private Duration reportInterval = Duration.seconds(15L);

    @Parameter(value = PREFIX + "unit_rates", required = true)
    private TimeUnit unitRates = TimeUnit.SECONDS;

    @Parameter(value = PREFIX + "unit_durations", required = true)
    private TimeUnit unitDurations = TimeUnit.MILLISECONDS;

    @Parameter(value = PREFIX + "uri", required = true)
    private URI uri;

    @Parameter(value = PREFIX + "time_precision", required = true)
    private TimeUnit timePrecision = TimeUnit.SECONDS;

    @Parameter(value = PREFIX + "connect_timeout", validator = PositiveDurationValidator.class)
    private Duration connectTimeout = Duration.seconds(5L);

    @Parameter(value = PREFIX + "read_timeout", validator = PositiveDurationValidator.class)
    private Duration readTimeout = Duration.seconds(5L);

    @Parameter(value = PREFIX + "socket_timeout", validator = PositiveDurationValidator.class)
    private Duration socketTimeout = Duration.seconds(5L);

    @Parameter(value = PREFIX + "tags", required = true, converter = StringMapConverter.class)
    private Map<String, String> tags = Collections.emptyMap();

    @Parameter(PREFIX + "group_gauges")
    private boolean groupGauges = false;

    @Parameter(PREFIX + "skip_idle_metrics")
    private boolean skipIdleMetrics = true;

    public boolean isEnabled() {
        return enabled;
    }

    public TimeUnit getUnitRates() {
        return unitRates;
    }

    public TimeUnit getUnitDurations() {
        return unitDurations;
    }

    public Duration getReportInterval() {
        return reportInterval;
    }

    public URI getUri() {
        return uri;
    }

    public TimeUnit getTimePrecision() {
        return timePrecision;
    }

    public Duration getConnectTimeout() {
        return connectTimeout;
    }

    public Duration getReadTimeout() {
        return readTimeout;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public boolean isGroupGauges() {
        return groupGauges;
    }

    public boolean isSkipIdleMetrics() {
        return skipIdleMetrics;
    }

    public Duration getSocketTimeout() {
        return socketTimeout;
    }

    @ValidatorMethod
    @SuppressWarnings("unused")
    public void validate() throws ValidationException {
        if (uri != null) {
            final String scheme = uri.getScheme();
            if (!Arrays.asList("http", "https", "tcp", "udp").contains(scheme)) {
                throw new ValidationException("Unsupported protocol \"" + scheme + "\".");
            }
        }
    }
}
