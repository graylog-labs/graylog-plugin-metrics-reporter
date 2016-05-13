/**
 * This file is part of Graylog Metrics Console Reporter Plugin.
 *
 * Graylog Metrics Console Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics Console Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics Console Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.console;

import com.github.joschi.jadconfig.Parameter;
import com.github.joschi.jadconfig.util.Duration;
import com.github.joschi.jadconfig.validators.PositiveDurationValidator;
import com.google.common.base.Strings;
import org.graylog2.plugin.PluginConfigBean;

import java.io.PrintStream;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class MetricsConsoleReporterConfiguration implements PluginConfigBean {
    private static final String PREFIX = "metrics_console_";

    @Parameter(PREFIX + "enabled")
    private boolean enabled = false;

    @Parameter(value = PREFIX + "report_interval", required = true, validator = PositiveDurationValidator.class)
    private Duration reportInterval = Duration.seconds(15L);

    @Parameter(value = PREFIX + "locale", required = true)
    private Locale locale = Locale.getDefault();

    @Parameter(value = PREFIX + "timezone", required = true)
    private TimeZone timeZone = TimeZone.getDefault();

    @Parameter(value = PREFIX + "output", required = true)
    private String outputStream = "stdout";

    @Parameter(value = PREFIX + "unit_rates", required = true)
    private TimeUnit unitRates = TimeUnit.SECONDS;

    @Parameter(value = PREFIX + "unit_durations", required = true)
    private TimeUnit unitDurations = TimeUnit.MILLISECONDS;

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

    public Locale getLocale() {
        return locale;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public PrintStream getOutputStream() {
        switch (Strings.nullToEmpty(outputStream).toLowerCase(Locale.ENGLISH)) {
            case "stderr":
            case "err":
                return System.err;
            case "stdout":
            case "out":
            default:
                return System.out;
        }
    }
}
