/**
 * This file is part of Graylog Metrics Elasticsearch Reporter Plugin.
 *
 * Graylog Metrics Elasticsearch Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics Elasticsearch Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics Elasticsearch Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.elasticsearch;

import com.github.joschi.jadconfig.Parameter;
import com.github.joschi.jadconfig.converters.StringListConverter;
import com.github.joschi.jadconfig.util.Duration;
import com.github.joschi.jadconfig.validators.PositiveDurationValidator;
import com.github.joschi.jadconfig.validators.PositiveIntegerValidator;
import com.github.joschi.jadconfig.validators.StringNotBlankValidator;
import com.google.common.collect.ImmutableMap;
import org.graylog.plugins.metrics.core.jadconfig.PatternListConverter;
import org.graylog.plugins.metrics.core.jadconfig.StringMapConverter;
import org.graylog2.plugin.PluginConfigBean;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class MetricsElasticsearchReporterConfiguration implements PluginConfigBean {
    private static final String PREFIX = "metrics_elasticsearch_";

    @Parameter(PREFIX + "enabled")
    private boolean enabled = false;

    @Parameter(value = PREFIX + "report_interval", required = true, validator = PositiveDurationValidator.class)
    private Duration reportInterval = Duration.seconds(15L);

    @Parameter(value = PREFIX + "unit_rates", required = true)
    private TimeUnit unitRates = TimeUnit.SECONDS;

    @Parameter(value = PREFIX + "unit_durations", required = true)
    private TimeUnit unitDurations = TimeUnit.MILLISECONDS;

    @Parameter(value = PREFIX + "connect_timeout", validator = PositiveDurationValidator.class)
    private Duration connectTimeout = Duration.seconds(5L);

    @Parameter(value = PREFIX + "additional_fields", required = true, converter = StringMapConverter.class)
    private Map<String, String> additionalFields = Collections.emptyMap();

    @Parameter(value = PREFIX + "hosts", required = true, converter = StringListConverter.class)
    private List<String> hosts = Collections.singletonList("localhost:9200");

    @Parameter(value = PREFIX + "index", validator = StringNotBlankValidator.class)
    private String index = "metrics";

    @Parameter(value = PREFIX + "bulk_size", validator = PositiveIntegerValidator.class)
    private int bulkSize = 2500;

    @Parameter(value = PREFIX + "timestamp_fieldname", validator = StringNotBlankValidator.class)
    private String timestampFieldname = "@timestamp";

    @Parameter(value = PREFIX + "index_date_format", validator = StringNotBlankValidator.class)
    private String indexDateFormat = "yyyy-MM";

    @Parameter(value = PREFIX + "prefix")
    private String prefix = null;

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

    public Duration getConnectTimeout() {
        return connectTimeout;
    }

    public Map<String, Object> getAdditionalFields() {
        return ImmutableMap.copyOf(additionalFields);
    }

    public List<String> getHosts() {
        return hosts;
    }

    public String getIndex() {
        return index;
    }

    public int getBulkSize() {
        return bulkSize;
    }

    public String getTimestampFieldname() {
        return timestampFieldname;
    }

    public String getIndexDateFormat() {
        return indexDateFormat;
    }

    public String getPrefix() {
        return prefix;
    }

    public List<Pattern> getIncludeMetrics() {
        return includeMetrics;
    }
}
