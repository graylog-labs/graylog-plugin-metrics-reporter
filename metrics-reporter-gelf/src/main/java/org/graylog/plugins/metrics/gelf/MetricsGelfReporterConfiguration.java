/**
 * This file is part of Graylog Metrics GELF Reporter Plugin.
 *
 * Graylog Metrics GELF Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics GELF Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics GELF Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.gelf;

import com.github.joschi.jadconfig.Parameter;
import com.github.joschi.jadconfig.util.Duration;
import com.github.joschi.jadconfig.validators.PositiveDurationValidator;
import com.github.joschi.jadconfig.validators.PositiveIntegerValidator;
import com.github.joschi.jadconfig.validators.StringNotBlankValidator;
import org.graylog.plugins.metrics.core.jadconfig.PatternListConverter;
import org.graylog.plugins.metrics.gelf.converters.GelfMessageLevelConverter;
import org.graylog.plugins.metrics.gelf.converters.GelfTransportsConverter;
import org.graylog2.gelfclient.GelfMessageLevel;
import org.graylog2.gelfclient.GelfTransports;
import org.graylog2.plugin.PluginConfigBean;

import java.io.File;
import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class MetricsGelfReporterConfiguration implements PluginConfigBean {
    private static final String PREFIX = "metrics_gelf_";

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

    @Parameter(value = PREFIX + "host", required = true)
    private InetSocketAddress host = InetSocketAddress.createUnresolved("localhost", 12201);

    @Parameter(value = PREFIX + "transport", converter = GelfTransportsConverter.class, required = true)
    private GelfTransports transport = GelfTransports.UDP;

    @Parameter(value = PREFIX + "level", converter = GelfMessageLevelConverter.class, required = true)
    private GelfMessageLevel level = GelfMessageLevel.INFO;

    @Parameter(value = PREFIX + "source", required = true, validator = StringNotBlankValidator.class)
    private String source = "metrics";

    @Parameter(value = PREFIX + "tls_enabled")
    private boolean tlsEnabled = false;

    @Parameter(value = PREFIX + "tls_cert_verification_enabled")
    private boolean tlsCertVerificationEnabled = false;

    @Parameter(value = PREFIX + "tls_trust_cert_chain_file")
    private File tlsTrustCertChainFile;

    @Parameter(value = PREFIX + "connect_timeout", required = true, validator = PositiveDurationValidator.class)
    private Duration connectTimeout = Duration.seconds(1L);

    @Parameter(value = PREFIX + "reconnect_delay", required = true, validator = PositiveDurationValidator.class)
    private Duration reconnectDelay = Duration.milliseconds(500L);

    @Parameter(value = PREFIX + "queue_size", required = true, validator = PositiveIntegerValidator.class)
    private int queueSize = 500;

    @Parameter(value = PREFIX + "send_buffer_size", required = true)
    private int sendBufferSize = -1;

    @Parameter(value = PREFIX + "max_in_flight_sends", required = true, validator = PositiveIntegerValidator.class)
    private int maxInFlightSends = 512;

    @Parameter(value = PREFIX + "tcp_keep_alive")
    private boolean tcpKeepAlive = false;

    @Parameter(value = PREFIX + "tcp_no_delay")
    private boolean tcpNoDelay = false;

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

    public InetSocketAddress getHost() {
        return host;
    }

    public GelfTransports getTransport() {
        return transport;
    }

    public GelfMessageLevel getLevel() {
        return level;
    }

    public String getSource() {
        return source;
    }

    public boolean isTlsEnabled() {
        return tlsEnabled;
    }

    public boolean isTlsCertVerificationEnabled() {
        return tlsCertVerificationEnabled;
    }

    public File getTlsTrustCertChainFile() {
        return tlsTrustCertChainFile;
    }

    public Duration getConnectTimeout() {
        return connectTimeout;
    }

    public Duration getReconnectDelay() {
        return reconnectDelay;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public int getSendBufferSize() {
        return sendBufferSize;
    }

    public int getMaxInFlightSends() {
        return maxInFlightSends;
    }

    public boolean isTcpKeepAlive() {
        return tcpKeepAlive;
    }

    public boolean isTcpNoDelay() {
        return tcpNoDelay;
    }

    public List<Pattern> getIncludeMetrics() {
        return includeMetrics;
    }
}
