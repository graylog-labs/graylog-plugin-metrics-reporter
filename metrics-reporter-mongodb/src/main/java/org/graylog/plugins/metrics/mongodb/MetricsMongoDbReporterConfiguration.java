/**
 * This file is part of Graylog Metrics MongoDB Reporter Plugin.
 *
 * Graylog Metrics MongoDB Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics MongoDB Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics MongoDB Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.mongodb;

import com.github.joschi.jadconfig.Parameter;
import com.github.joschi.jadconfig.util.Duration;
import com.github.joschi.jadconfig.validators.PositiveDurationValidator;
import com.google.common.collect.ImmutableMap;
import com.mongodb.MongoClientURI;
import org.graylog.plugins.metrics.mongodb.jadconfig.StringMapConverter;
import org.graylog2.plugin.PluginConfigBean;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MetricsMongoDbReporterConfiguration implements PluginConfigBean {
    private static final String PREFIX = "metrics_mongodb_";

    @Parameter(PREFIX + "enabled")
    private boolean enabled = false;

    @Parameter(value = PREFIX + "report_interval", required = true, validator = PositiveDurationValidator.class)
    private Duration reportInterval = Duration.seconds(15L);

    @Parameter(value = PREFIX + "unit_rates", required = true)
    private TimeUnit unitRates = TimeUnit.SECONDS;

    @Parameter(value = PREFIX + "unit_durations", required = true)
    private TimeUnit unitDurations = TimeUnit.MILLISECONDS;

    @Parameter(value = PREFIX + "uri", required = true)
    private MongoClientURI uri;

    @Parameter(value = PREFIX + "additionalFields", required = true, converter = StringMapConverter.class)
    private Map<String, String> additionalFields = Collections.emptyMap();

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

    public MongoClientURI getUri() {
        return uri;
    }

    public Map<String, Object> getAdditionalFields() {
        return ImmutableMap.copyOf(additionalFields);
    }
}
