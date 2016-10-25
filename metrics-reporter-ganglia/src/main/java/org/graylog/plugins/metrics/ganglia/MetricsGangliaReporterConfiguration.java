/**
 * This file is part of Graylog Metrics Ganglia Reporter Plugin.
 *
 * Graylog Metrics Ganglia Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics Ganglia Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics Ganglia Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.ganglia;

import com.github.joschi.jadconfig.Parameter;
import com.github.joschi.jadconfig.util.Duration;
import com.github.joschi.jadconfig.validators.InetPortValidator;
import com.github.joschi.jadconfig.validators.PositiveDurationValidator;
import com.github.joschi.jadconfig.validators.PositiveIntegerValidator;
import info.ganglia.gmetric4j.gmetric.GMetric;
import org.graylog.plugins.metrics.core.jadconfig.PatternListConverter;
import org.graylog2.plugin.PluginConfigBean;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class MetricsGangliaReporterConfiguration implements PluginConfigBean {
    private static final String PREFIX = "metrics_ganglia_";

    @Parameter(PREFIX + "enabled")
    private boolean enabled = false;

    @Parameter(value = PREFIX + "group", required = true)
    private String group = "127.0.0.1";

    @Parameter(value = PREFIX + "spoof", required = false)
    private String spoof = "";

    @Parameter(value = PREFIX + "port", required = true, validator = InetPortValidator.class)
    private int port = 8649;

    @Parameter(PREFIX + "addressing_mode")
    private GMetric.UDPAddressingMode UDPAddressingMode = null;

    @Parameter(value = PREFIX + "ttl", validator = PositiveIntegerValidator.class)
    private int ttl = 1;

    @Parameter(value = PREFIX + "version31x")
    private boolean ganglia31x = false;

    @Parameter(value = PREFIX + "uuid")
    private UUID uuid = null;

    @Parameter(value = PREFIX + "dmax", validator = PositiveDurationValidator.class)
    private int dMax = 0;

    @Parameter(value = PREFIX + "tmax", validator = PositiveDurationValidator.class)
    private int tMax = 60;

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

    public String getPrefix() {
        return prefix;
    }

    public TimeUnit getUnitRates() {
        return unitRates;
    }

    public TimeUnit getUnitDurations() {
        return unitDurations;
    }

    public String getGroup() {
        return group;
    }

    public String getSpoof() {
        return spoof;
    }

    public int getPort() {
        return port;
    }

    public GMetric.UDPAddressingMode getUDPAddressingMode() {
        if (UDPAddressingMode == null) {
            try {
                return GMetric.UDPAddressingMode.getModeForAddress(getGroup());
            } catch (IOException e) {
                return GMetric.UDPAddressingMode.UNICAST;
            }
        } else {
            return UDPAddressingMode;
        }
    }

    public int getTtl() {
        return ttl;
    }

    public boolean isGanglia31x() {
        return ganglia31x;
    }

    public UUID getUUID() {
        return uuid;
    }

    public int getDMax() {
        return dMax;
    }

    public int getTMax() {
        return tMax;
    }

    public List<Pattern> getIncludeMetrics() {
        return includeMetrics;
    }
}
