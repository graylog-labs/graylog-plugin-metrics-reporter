/**
 * This file is part of Graylog Metrics SLF4J Reporter Plugin.
 *
 * Graylog Metrics SLF4J Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics SLF4J Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics SLF4J Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.slf4j;

import com.codahale.metrics.Slf4jReporter;
import com.github.joschi.jadconfig.Parameter;
import com.github.joschi.jadconfig.util.Duration;
import com.github.joschi.jadconfig.validators.PositiveDurationValidator;
import org.graylog.plugins.metrics.core.jadconfig.PatternListConverter;
import org.graylog.plugins.metrics.slf4j.converters.LoggerConverter;
import org.graylog.plugins.metrics.slf4j.converters.LoggingLevelConverter;
import org.graylog.plugins.metrics.slf4j.converters.MarkerConverter;
import org.graylog2.plugin.PluginConfigBean;
import org.slf4j.Logger;
import org.slf4j.Marker;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class MetricsSlf4jReporterConfiguration implements PluginConfigBean {
    private static final String PREFIX = "metrics_slf4j_";

    @Parameter(PREFIX + "enabled")
    private boolean enabled = false;

    @Parameter(value = PREFIX + "report_interval", required = true, validator = PositiveDurationValidator.class)
    private Duration reportInterval = Duration.seconds(15L);

    @Parameter(value = PREFIX + "unit_rates", required = true)
    private TimeUnit unitRates = TimeUnit.SECONDS;

    @Parameter(value = PREFIX + "unit_durations", required = true)
    private TimeUnit unitDurations = TimeUnit.MILLISECONDS;

    @Parameter(value = PREFIX + "marker", converter = MarkerConverter.class)
    private Marker marker = null;

    @Parameter(value = PREFIX + "logger", required = true, converter = LoggerConverter.class)
    private Logger logger;

    @Parameter(value = PREFIX + "level", required = true, converter = LoggingLevelConverter.class)
    private Slf4jReporter.LoggingLevel level = Slf4jReporter.LoggingLevel.TRACE;

    @Parameter(value = PREFIX + "include_metrics", converter = PatternListConverter.class)
    private List<Pattern> includeMetrics = Collections.singletonList(Pattern.compile(".*"));

    public boolean isEnabled() {
        return enabled;
    }

    public Marker getMarker() {
        return marker;
    }

    public Logger getLogger() {
        return logger;
    }

    public Slf4jReporter.LoggingLevel getLevel() {
        return level;
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

    public List<Pattern> getIncludeMetrics() {
        return includeMetrics;
    }
}
