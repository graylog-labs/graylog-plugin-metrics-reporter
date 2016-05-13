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

import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteSender;
import com.codahale.metrics.graphite.GraphiteUDP;
import com.codahale.metrics.graphite.PickledGraphite;
import org.graylog.plugins.metrics.graphite.GraphiteProtocol;
import org.graylog.plugins.metrics.graphite.MetricsGraphiteReporterConfiguration;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GraphiteSenderProviderTest {
    @Test
    public void getReturnsGraphite() throws Exception {
        final MetricsGraphiteReporterConfiguration configuration = new MetricsGraphiteReporterConfiguration() {
            @Override
            public GraphiteProtocol getProtocol() {
                return GraphiteProtocol.TCP;
            }
        };
        final GraphiteSenderProvider provider = new GraphiteSenderProvider(configuration);

        final GraphiteSender graphiteSender = provider.get();
        assertTrue(graphiteSender instanceof Graphite);
        assertFalse(graphiteSender.isConnected());
    }

    @Test
    public void getReturnsGraphiteUDP() throws Exception {
        final MetricsGraphiteReporterConfiguration configuration = new MetricsGraphiteReporterConfiguration() {
            @Override
            public GraphiteProtocol getProtocol() {
                return GraphiteProtocol.UDP;
            }
        };
        final GraphiteSenderProvider provider = new GraphiteSenderProvider(configuration);

        final GraphiteSender graphiteSender = provider.get();
        assertTrue(graphiteSender instanceof GraphiteUDP);
        assertFalse(graphiteSender.isConnected());
    }

    @Test
    public void getReturnsGraphitePickledGraphite() throws Exception {
        final MetricsGraphiteReporterConfiguration configuration = new MetricsGraphiteReporterConfiguration() {
            @Override
            public GraphiteProtocol getProtocol() {
                return GraphiteProtocol.PICKLE;
            }
        };
        final GraphiteSenderProvider provider = new GraphiteSenderProvider(configuration);

        final GraphiteSender graphiteSender = provider.get();
        assertTrue(graphiteSender instanceof PickledGraphite);
        assertFalse(graphiteSender.isConnected());
    }

}