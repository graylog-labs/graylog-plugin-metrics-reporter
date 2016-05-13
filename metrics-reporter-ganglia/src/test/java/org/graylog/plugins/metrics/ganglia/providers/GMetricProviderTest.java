/**
 * This file is part of Graylog Metrics Ganglia Reporter Plugin.
 *
 * Graylog Metrics Ganglia Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics Ganglia Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics Ganglia Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.ganglia.providers;

import info.ganglia.gmetric4j.gmetric.GMetric;
import org.graylog.plugins.metrics.ganglia.MetricsGangliaReporterConfiguration;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class GMetricProviderTest {
    @Test
    public void getReturnsGMetricUsingUnicast() throws Exception {
        final MetricsGangliaReporterConfiguration configuration = new MetricsGangliaReporterConfiguration() {
            @Override
            public GMetric.UDPAddressingMode getUDPAddressingMode() {
                return GMetric.UDPAddressingMode.UNICAST;
            }
        };
        final GMetricProvider provider = new GMetricProvider(configuration);

        final GMetric metric = provider.get();
        assertNotNull(metric);
    }

    @Test
    public void getReturnsGMetricUsingMulticast() throws Exception {
        final MetricsGangliaReporterConfiguration configuration = new MetricsGangliaReporterConfiguration() {
            @Override
            public GMetric.UDPAddressingMode getUDPAddressingMode() {
                return GMetric.UDPAddressingMode.MULTICAST;
            }
        };
        final GMetricProvider provider = new GMetricProvider(configuration);

        final GMetric metric = provider.get();
        assertNotNull(metric);
    }
}