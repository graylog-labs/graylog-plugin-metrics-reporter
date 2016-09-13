/**
 * This file is part of Graylog Metrics CSV Reporter Plugin.
 *
 * Graylog Metrics CSV Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics CSV Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics CSV Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.csv;

import com.github.joschi.jadconfig.Parameter;
import com.github.joschi.jadconfig.util.Duration;
import com.github.joschi.jadconfig.validators.DirectoryWritableValidator;
import com.github.joschi.jadconfig.validators.PositiveDurationValidator;
import org.graylog.plugins.metrics.core.jadconfig.PatternListConverter;
import org.graylog2.plugin.PluginConfigBean;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class MetricsCsvReporterConfiguration implements PluginConfigBean {
    private static final String PREFIX = "metrics_csv_";

    @Parameter(PREFIX + "enabled")
    private boolean enabled = false;

    @Parameter(value = PREFIX + "report_interval", required = true, validator = PositiveDurationValidator.class)
    private Duration reportInterval = Duration.seconds(15L);

    @Parameter(value = PREFIX + "locale", required = true)
    private Locale locale = Locale.getDefault();

    @Parameter(value = PREFIX + "directory", required = true, validator = DirectoryWritableValidator.class)
    private File directory = null;

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

    public TimeUnit getUnitRates() {
        return unitRates;
    }

    public TimeUnit getUnitDurations() {
        return unitDurations;
    }

    public Locale getLocale() {
        return locale;
    }

    public File getDirectory() {
        return directory;
    }

    public List<Pattern> getIncludeMetrics() {
        return includeMetrics;
    }
}
