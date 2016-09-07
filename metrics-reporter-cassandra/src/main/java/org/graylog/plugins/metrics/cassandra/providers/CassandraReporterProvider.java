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
package org.graylog.plugins.metrics.cassandra.providers;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.cassandra.Cassandra;
import com.codahale.metrics.cassandra.CassandraReporter;
import org.graylog.plugins.metrics.cassandra.MetricsCassandraReporterConfiguration;
import org.graylog.plugins.metrics.core.RegexMetricFilter;

import javax.inject.Inject;
import javax.inject.Provider;

import static java.util.Objects.requireNonNull;

public class CassandraReporterProvider implements Provider<CassandraReporter> {
    private final MetricsCassandraReporterConfiguration configuration;
    private final Cassandra cassandra;
    private final MetricRegistry metricRegistry;

    @Inject
    public CassandraReporterProvider(MetricsCassandraReporterConfiguration configuration,
                                     Cassandra cassandra,
                                     MetricRegistry metricRegistry) {
        this.configuration = requireNonNull(configuration);
        this.cassandra = requireNonNull(cassandra);
        this.metricRegistry = requireNonNull(metricRegistry);
    }

    @Override
    public CassandraReporter get() {
        return CassandraReporter.forRegistry(metricRegistry)
                .prefixedWith(configuration.getPrefix())
                .convertDurationsTo(configuration.getUnitDurations())
                .convertRatesTo(configuration.getUnitRates())
                .filter(new RegexMetricFilter(configuration.getIncludeMetrics()))
                .build(cassandra);
    }
}
