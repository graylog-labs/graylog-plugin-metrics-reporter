/**
 * This file is part of Graylog Metrics Librato Reporter Plugin.
 *
 * Graylog Metrics Librato Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics Librato Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics Librato Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.librato;

import com.github.joschi.jadconfig.Parameter;
import com.github.joschi.jadconfig.util.Duration;
import com.github.joschi.jadconfig.validators.PositiveDurationValidator;
import com.github.joschi.jadconfig.validators.StringNotBlankValidator;
import org.graylog2.plugin.PluginConfigBean;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class MetricsLibratoReporterConfiguration implements PluginConfigBean {
    private static final String PREFIX = "metrics_librato_";

    @Parameter(PREFIX + "enabled")
    private boolean enabled = false;

    @Parameter(value = PREFIX + "report_interval", required = true, validator = PositiveDurationValidator.class)
    private Duration reportInterval = Duration.seconds(15L);

    @Parameter(value = PREFIX + "unit_rates", required = true)
    private TimeUnit unitRates = TimeUnit.SECONDS;

    @Parameter(value = PREFIX + "unit_durations", required = true)
    private TimeUnit unitDurations = TimeUnit.MILLISECONDS;

    @Parameter(value = PREFIX + "username", required = true, validator = StringNotBlankValidator.class)
    private String username;

    @Parameter(value = PREFIX + "token", required = true, validator = StringNotBlankValidator.class)
    private String token;

    @Parameter(value = PREFIX + "source", required = true, validator = StringNotBlankValidator.class)
    private String source;

    @Parameter(value = PREFIX + "name", required = true, validator = StringNotBlankValidator.class)
    private String name = "Graylog";

    @Parameter(PREFIX + "prefix")
    private String prefix;

    @Parameter(PREFIX + "prefix_delimiter")
    private String prefixDelimiter = ".";

    @Parameter(value = PREFIX + "timeout", required = true, validator = PositiveDurationValidator.class)
    private Duration timeout = Duration.seconds(5L);

    @Parameter(value = PREFIX + "delete_idle_stats")
    private boolean deleteIdleStats = true;

    @Parameter(value = PREFIX + "omit_complex_gauges")
    private boolean omitComplexGauges = false;

    @Parameter(value = PREFIX + "source_regex")
    private Pattern sourceRegex;

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

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public String getSource() {
        return source;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getPrefixDelimiter() {
        return prefixDelimiter;
    }

    public Duration getTimeout() {
        return timeout;
    }

    public boolean isDeleteIdleStats() {
        return deleteIdleStats;
    }

    public boolean isOmitComplexGauges() {
        return omitComplexGauges;
    }

    public Pattern getSourceRegex() {
        return sourceRegex;
    }
}
