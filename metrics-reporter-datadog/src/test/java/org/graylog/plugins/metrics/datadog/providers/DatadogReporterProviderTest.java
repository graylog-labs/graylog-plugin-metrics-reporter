/**
 * This file is part of Graylog Metrics Datadog Reporter Plugin.
 *
 * Graylog Metrics Datadog Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics Datadog Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics Datadog Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.datadog.providers;

import com.codahale.metrics.MetricRegistry;
import org.coursera.metrics.datadog.DatadogReporter;
import org.coursera.metrics.datadog.transport.Transport;
import org.coursera.metrics.datadog.transport.UdpTransport;
import org.graylog.plugins.metrics.datadog.MetricsDatadogReporterConfiguration;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class DatadogReporterProviderTest {
    @Test
    public void get() throws Exception {
        final MetricsDatadogReporterConfiguration configuration = new MetricsDatadogReporterConfiguration();
        final Transport graphiteSender = new UdpTransport.Builder().build();
        final MetricRegistry metricRegistry = new MetricRegistry();
        final DatadogReporterProvider provider = new DatadogReporterProvider(configuration, graphiteSender, metricRegistry);

        final DatadogReporter reporter = provider.get();
        assertNotNull(reporter);
    }
}