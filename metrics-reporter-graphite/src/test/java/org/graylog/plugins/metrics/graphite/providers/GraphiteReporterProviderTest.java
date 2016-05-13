/**
 * This file is part of Graylog Metrics Graphite Reporter Plugin.
 *
 * Graylog Metrics Graphite Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics Graphite Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics Graphite Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.graphite.providers;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.GraphiteReporter;
import com.codahale.metrics.graphite.GraphiteSender;
import com.codahale.metrics.graphite.GraphiteUDP;
import org.graylog.plugins.metrics.graphite.MetricsGraphiteReporterConfiguration;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class GraphiteReporterProviderTest {
    @Test
    public void get() throws Exception {
        final MetricsGraphiteReporterConfiguration configuration = new MetricsGraphiteReporterConfiguration();
        final GraphiteSender graphiteSender = new GraphiteUDP("127.0.0.1", 12345);
        final MetricRegistry metricRegistry = new MetricRegistry();
        final GraphiteReporterProvider provider = new GraphiteReporterProvider(configuration, graphiteSender, metricRegistry);

        final GraphiteReporter reporter = provider.get();
        assertNotNull(reporter);
    }
}