/**
 * This file is part of Graylog Metrics OpenTSDB Reporter Plugin.
 *
 * Graylog Metrics OpenTSDB Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics OpenTSDB Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics OpenTSDB Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.opentsdb;

import com.github.joschi.jadconfig.Parameter;
import com.github.joschi.jadconfig.util.Duration;
import com.github.joschi.jadconfig.validators.PositiveDurationValidator;
import com.github.joschi.jadconfig.validators.PositiveIntegerValidator;
import com.github.sps.metrics.opentsdb.OpenTsdb;
import com.google.common.net.HostAndPort;
import org.graylog.plugins.metrics.core.jadconfig.PatternListConverter;
import org.graylog.plugins.metrics.core.jadconfig.StringMapConverter;
import org.graylog.plugins.metrics.opentsdb.jadconfig.OpenTsdbProtocolConverter;
import org.graylog2.plugin.PluginConfigBean;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class MetricsOpenTsdbReporterConfiguration implements PluginConfigBean {
    private static final String PREFIX = "metrics_opentsdb_";

    public static final int DEFAULT_PORT = 4242;

    @Parameter(PREFIX + "enabled")
    private boolean enabled = false;

    @Parameter(value = PREFIX + "report_interval", required = true, validator = PositiveDurationValidator.class)
    private Duration reportInterval = Duration.seconds(15L);

    @Parameter(value = PREFIX + "unit_rates", required = true)
    private TimeUnit unitRates = TimeUnit.SECONDS;

    @Parameter(value = PREFIX + "unit_durations", required = true)
    private TimeUnit unitDurations = TimeUnit.MILLISECONDS;

    @Parameter(PREFIX + "prefix")
    private String prefix = null;

    @Parameter(value = PREFIX + "protocol", required = true, converter = OpenTsdbProtocolConverter.class)
    private OpenTsdbProtocol protocol = OpenTsdbProtocol.TELNET;

    @Parameter(value = PREFIX + "telnet_address", required = true)
    private HostAndPort telnetAddress = HostAndPort.fromParts("localhost", DEFAULT_PORT);

    @Parameter(value = PREFIX + "http_base_url", required = true)
    private URI httpBaseUrl = URI.create("http://localhost:" + DEFAULT_PORT + "/");

    @Parameter(value = PREFIX + "http_connect_timeout", validator = PositiveIntegerValidator.class)
    private int httpConnectTimeout = OpenTsdb.CONN_TIMEOUT_DEFAULT_MS;

    @Parameter(value = PREFIX + "http_read_timeout", validator = PositiveIntegerValidator.class)
    private int httpReadTimeout = OpenTsdb.READ_TIMEOUT_DEFAULT_MS;

    @Parameter(PREFIX + "http_enable_gzip")
    private boolean httpEnableGzip = true;

    @Parameter(value = PREFIX + "batch_size", validator = PositiveIntegerValidator.class)
    private int batchSize = OpenTsdb.DEFAULT_BATCH_SIZE_LIMIT;

    @Parameter(PREFIX + "counter_gauge_decorations")
    private boolean counterGaugeDecorations = true;

    @Parameter(value = PREFIX + "tags", converter = StringMapConverter.class)
    private Map<String, String> tags = Collections.emptyMap();

    @Parameter(value = PREFIX + "include_metrics", converter = PatternListConverter.class)
    private List<Pattern> includeMetrics = Collections.singletonList(Pattern.compile(".*"));

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

    public String getPrefix() {
        return prefix;
    }

    public OpenTsdbProtocol getProtocol() {
        return protocol;
    }

    public HostAndPort getTelnetAddress() {
        return telnetAddress;
    }

    public URI getHttpBaseUrl() {
        return httpBaseUrl;
    }

    public int getHttpConnectTimeout() {
        return httpConnectTimeout;
    }

    public int getHttpReadTimeout() {
        return httpReadTimeout;
    }

    public boolean isHttpEnableGzip() {
        return httpEnableGzip;
    }

    public int getBatchSize() {
        return batchSize;
    }

    public boolean isCounterGaugeDecorations() {
        return counterGaugeDecorations;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public List<Pattern> getIncludeMetrics() {
        return includeMetrics;
    }
}
