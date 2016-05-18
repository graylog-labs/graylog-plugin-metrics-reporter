/**
 * This file is part of Graylog Metrics Cassandra Reporter Plugin.
 *
 * Graylog Metrics Cassandra Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics Cassandra Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics Cassandra Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.cassandra;

import com.datastax.driver.core.ConsistencyLevel;
import com.github.joschi.jadconfig.Parameter;
import com.github.joschi.jadconfig.converters.StringListConverter;
import com.github.joschi.jadconfig.util.Duration;
import com.github.joschi.jadconfig.validators.InetPortValidator;
import com.github.joschi.jadconfig.validators.PositiveDurationValidator;
import com.github.joschi.jadconfig.validators.PositiveIntegerValidator;
import com.github.joschi.jadconfig.validators.StringNotBlankValidator;
import org.graylog.plugins.metrics.cassandra.jadconfig.ConsistencyLevelConverter;
import org.graylog2.plugin.PluginConfigBean;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MetricsCassandraReporterConfiguration implements PluginConfigBean {
    private static final String PREFIX = "metrics_cassandra_";

    @Parameter(PREFIX + "enabled")
    private boolean enabled = false;

    @Parameter(value = PREFIX + "report_interval", required = true, validator = PositiveDurationValidator.class)
    private Duration reportInterval = Duration.seconds(15L);

    @Parameter(value = PREFIX + "unit_rates", required = true)
    private TimeUnit unitRates = TimeUnit.SECONDS;

    @Parameter(value = PREFIX + "unit_durations", required = true)
    private TimeUnit unitDurations = TimeUnit.MILLISECONDS;

    @Parameter(value = PREFIX + "prefix")
    private String prefix;

    @Parameter(value = PREFIX + "addresses", required = true, converter = StringListConverter.class)
    private List<String> addresses = Collections.singletonList("127.0.0.1");

    @Parameter(value = PREFIX + "port", required = true, validator = InetPortValidator.class)
    private int port = 9042;

    @Parameter(value = PREFIX + "keyspace", required = true, validator = StringNotBlankValidator.class)
    private String keyspace = "graylog";

    @Parameter(value = PREFIX + "table", required = true, validator = StringNotBlankValidator.class)
    private String table = "metrics";

    @Parameter(value = PREFIX + "ttl", required = true, validator = PositiveIntegerValidator.class)
    private int ttl = 60;

    @Parameter(value = PREFIX + "consistency", required = true, converter = ConsistencyLevelConverter.class)
    private ConsistencyLevel consistency = ConsistencyLevel.ONE;


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

    public List<String> getAddresses() {
        return addresses;
    }

    public int getPort() {
        return port;
    }

    public String getKeyspace() {
        return keyspace;
    }

    public String getTable() {
        return table;
    }

    public int getTtl() {
        return ttl;
    }

    public String getConsistency() {
        return consistency.name();
    }
}
