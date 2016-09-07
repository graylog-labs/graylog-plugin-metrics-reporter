/**
 * This file is part of Graylog Metrics Graphite Reporter Plugin.
 *
 * Graylog Metrics Graphite Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics Graphite Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics Graphite Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.graphite;

import com.github.joschi.jadconfig.Parameter;
import com.github.joschi.jadconfig.util.Duration;
import com.github.joschi.jadconfig.validators.PositiveDurationValidator;
import com.github.joschi.jadconfig.validators.PositiveIntegerValidator;
import org.graylog.plugins.metrics.core.jadconfig.PatternListConverter;
import org.graylog.plugins.metrics.graphite.converters.GraphiteProtocolConverter;
import org.graylog2.plugin.PluginConfigBean;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class MetricsGraphiteReporterConfiguration implements PluginConfigBean {
    private static final String PREFIX = "metrics_graphite_";

    @Parameter(PREFIX + "enabled")
    private boolean enabled = false;

    @Parameter(value = PREFIX + "address", required = true)
    private InetSocketAddress address = new InetSocketAddress("127.0.0.1", 2003);

    @Parameter(value = PREFIX + "charset", required = true)
    private Charset charset = StandardCharsets.UTF_8;

    @Parameter(value = PREFIX + "protocol", required = true, converter = GraphiteProtocolConverter.class)
    private GraphiteProtocol protocol = GraphiteProtocol.TCP;

    @Parameter(value = PREFIX + "pickle_batch_size", validator = PositiveIntegerValidator.class)
    private int pickleBatchSize = 100;

    @Parameter(value = PREFIX + "report_interval", required = true, validator = PositiveDurationValidator.class)
    private Duration reportInterval = Duration.seconds(15L);

    @Parameter(PREFIX + "prefix")
    private String prefix = null;

    @Parameter(value = PREFIX + "unit_rates", required = true)
    private TimeUnit unitRates = TimeUnit.SECONDS;

    @Parameter(value = PREFIX + "unit_durations", required = true)
    private TimeUnit unitDurations = TimeUnit.MILLISECONDS;

    @Parameter(value = PREFIX + "include_metrics", converter = PatternListConverter.class)
    private List<Pattern> includeMetrics = Collections.singletonList(Pattern.compile(".*"));

    public boolean isEnabled() {
        return enabled;
    }

    public Duration getReportInterval() {
        return reportInterval;
    }

    public InetSocketAddress getAddress() {
        return address;
    }

    public Charset getCharset() {
        return charset;
    }

    public GraphiteProtocol getProtocol() {
        return protocol;
    }

    public int getPickleBatchSize() {
        return pickleBatchSize;
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

    public List<Pattern> getIncludeMetrics() {
        return includeMetrics;
    }
}
