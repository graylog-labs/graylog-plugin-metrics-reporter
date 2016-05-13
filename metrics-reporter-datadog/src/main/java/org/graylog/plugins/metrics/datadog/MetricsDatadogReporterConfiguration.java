/**
 * This file is part of Graylog Metrics Datadog Reporter Plugin.
 *
 * Graylog Metrics Datadog Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics Datadog Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics Datadog Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.datadog;

import com.github.joschi.jadconfig.Parameter;
import com.github.joschi.jadconfig.util.Duration;
import com.github.joschi.jadconfig.validators.PositiveDurationValidator;
import com.google.common.net.HostAndPort;
import org.graylog.plugins.metrics.datadog.converters.DatadogTransportConverter;
import org.graylog2.plugin.PluginConfigBean;

import java.util.concurrent.TimeUnit;

public class MetricsDatadogReporterConfiguration implements PluginConfigBean {
    private static final String PREFIX = "metrics_datadog_";

    @Parameter(PREFIX + "enabled")
    private boolean enabled = false;

    @Parameter(value = PREFIX + "transport", required = true, converter = DatadogTransportConverter.class)
    private DatadogTransport transport = DatadogTransport.HTTP;

    @Parameter(value = PREFIX + "report_interval", required = true, validator = PositiveDurationValidator.class)
    private Duration reportInterval = Duration.seconds(15L);

    @Parameter(PREFIX + "prefix")
    private String prefix;

    @Parameter(PREFIX + "api_key")
    private String apiKey;

    @Parameter(value = PREFIX + "unit_rates", required = true)
    private TimeUnit unitRates = TimeUnit.SECONDS;

    @Parameter(value = PREFIX + "unit_durations", required = true)
    private TimeUnit unitDurations = TimeUnit.MILLISECONDS;

    @Parameter(value = PREFIX + "http_connect_timeout", validator = PositiveDurationValidator.class)
    private Duration httpConnectTimeout = Duration.seconds(5L);

    @Parameter(value = PREFIX + "http_socket_timeout", validator = PositiveDurationValidator.class)
    private Duration httpSocketTimeout = Duration.seconds(5L);

    @Parameter(PREFIX + "http_proxy")
    private HostAndPort httpProxy;

    @Parameter(PREFIX + "udp_address")
    private HostAndPort udpAddress = HostAndPort.fromParts("localhost", 8125);

    @Parameter(PREFIX + "udp_prefix")
    private String udpPrefix;

    @Parameter(PREFIX + "hostname")
    private String hostname;

    @Parameter(PREFIX + "detect_ec2_hostname")
    private boolean ec2Instance = false;

    public boolean isEnabled() {
        return enabled;
    }

    public Duration getReportInterval() {
        return reportInterval;
    }

    public DatadogTransport getTransport() {
        return transport;
    }

    public String getPrefix() {
        return prefix;
    }

    public TimeUnit getUnitRates() {
        return unitRates;
    }

    public TimeUnit getUnitDurations() {
        return unitDurations;
    }

    public Duration getHttpConnectTimeout() {
        return httpConnectTimeout;
    }

    public Duration getHttpSocketTimeout() {
        return httpSocketTimeout;
    }

    public String getApiKey() {
        return apiKey;
    }

    public HostAndPort getHttpProxy() {
        return httpProxy;
    }

    public String getUdpPrefix() {
        return udpPrefix;
    }

    public HostAndPort getUdpAddress() {
        return udpAddress;
    }

    public String getHostname() {
        return hostname;
    }

    public boolean isEC2Instance() {
        return ec2Instance;
    }
}
