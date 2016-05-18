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

import com.codahale.metrics.cassandra.Cassandra;
import com.google.common.base.Throwables;
import org.graylog.plugins.metrics.cassandra.MetricsCassandraReporterConfiguration;

import javax.inject.Inject;
import javax.inject.Provider;

import static java.util.Objects.requireNonNull;

public class CassandraProvider implements Provider<Cassandra> {
    private final MetricsCassandraReporterConfiguration configuration;

    @Inject
    public CassandraProvider(MetricsCassandraReporterConfiguration configuration) {
        this.configuration = requireNonNull(configuration);
    }

    @Override
    public Cassandra get() {
        try {
            return new Cassandra(configuration.getAddresses(),
                    configuration.getKeyspace(),
                    configuration.getTable(),
                    configuration.getTtl(),
                    configuration.getPort(),
                    configuration.getConsistency());
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }
}
