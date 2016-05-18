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
import org.graylog.plugins.metrics.cassandra.MetricsCassandraReporterConfiguration;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class CassandraProviderTest {
    @Test
    @Ignore("Requires running Cassandra instance")
    public void get() throws Exception {
        final MetricsCassandraReporterConfiguration configuration = new MetricsCassandraReporterConfiguration();
        final CassandraProvider provider = new CassandraProvider(configuration);
        final Cassandra cassandra = provider.get();
        assertNotNull(cassandra);
    }
}