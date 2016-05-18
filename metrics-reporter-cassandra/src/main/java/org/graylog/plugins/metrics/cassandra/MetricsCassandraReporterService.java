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

import com.codahale.metrics.cassandra.CassandraReporter;
import com.github.joschi.jadconfig.util.Duration;
import com.google.common.util.concurrent.AbstractIdleService;

import javax.inject.Inject;

import static java.util.Objects.requireNonNull;

public class MetricsCassandraReporterService extends AbstractIdleService {
    private final MetricsCassandraReporterConfiguration configuration;
    private final CassandraReporter cassandraReporter;

    @Inject
    public MetricsCassandraReporterService(CassandraReporter cassandraReporter, MetricsCassandraReporterConfiguration configuration) {
        this.cassandraReporter = requireNonNull(cassandraReporter);
        this.configuration = requireNonNull(configuration);
    }

    @Override
    protected void startUp() throws Exception {
        if (configuration.isEnabled()) {
            final Duration reportInterval = configuration.getReportInterval();
            cassandraReporter.start(reportInterval.getQuantity(), reportInterval.getUnit());
        }
    }

    @Override
    protected void shutDown() throws Exception {
        if (configuration.isEnabled()) {
            cassandraReporter.stop();
        }
    }
}
