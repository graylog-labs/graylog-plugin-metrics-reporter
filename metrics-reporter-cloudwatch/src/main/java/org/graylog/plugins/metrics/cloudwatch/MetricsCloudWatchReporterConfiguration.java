/**
 * This file is part of Graylog Metrics CloudWatch Reporter Plugin.
 *
 * Graylog Metrics CloudWatch Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics CloudWatch Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics CloudWatch Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.cloudwatch;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.graylog.plugins.metrics.core.jadconfig.PatternListConverter;
import org.graylog2.plugin.PluginConfigBean;

import com.github.joschi.jadconfig.Parameter;
import com.github.joschi.jadconfig.util.Duration;
import com.github.joschi.jadconfig.validators.PositiveDurationValidator;

public class MetricsCloudWatchReporterConfiguration implements PluginConfigBean {
    private static final String PREFIX = "metrics_cloudwatch_";

    @Parameter(PREFIX + "enabled")
    private boolean enabled = false;

    @Parameter(value = PREFIX + "report_interval", required = true, validator = PositiveDurationValidator.class)
    private Duration reportInterval = Duration.seconds(15L);

    @Parameter(value = PREFIX + "unit_rates", required = true)
    private TimeUnit unitRates = TimeUnit.SECONDS;

    @Parameter(value = PREFIX + "unit_durations", required = true)
    private TimeUnit unitDurations = TimeUnit.MILLISECONDS;

    @Parameter(value = PREFIX + "namespace", required = true)
    private String namespace = "Graylog";
    
    @Parameter(value = PREFIX + "timestamp_local")
    private boolean timestampLocal = false;

    @Parameter(value = PREFIX + "include_metrics", converter = PatternListConverter.class)
    private List<Pattern> includeMetrics = Collections.singletonList(Pattern.compile(".*"));
    
    @Parameter(value = PREFIX + "dimensions", required = true)
    private String dimensions;

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

    public String getNamespace() {
        return namespace;
    }
    
    public boolean getTimestampLocal() {
    	return timestampLocal;
    }

    public List<Pattern> getIncludeMetrics() {
        return includeMetrics;
    }
    
    public String getDimensions() {
    	return dimensions;
    }
}
