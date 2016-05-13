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

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ganglia.GangliaReporter;
import info.ganglia.gmetric4j.gmetric.GMetric;
import org.graylog.plugins.metrics.ganglia.MetricsGangliaReporterConfiguration;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class GangliaReporterProviderTest {
    @Test
    public void get() throws Exception {
        final MetricsGangliaReporterConfiguration configuration = new MetricsGangliaReporterConfiguration();
        final GMetric graphiteSender = new GMetric("127.0.0.1", 12345, null, 23);
        final MetricRegistry metricRegistry = new MetricRegistry();
        final GangliaReporterProvider provider = new GangliaReporterProvider(configuration, graphiteSender, metricRegistry);

        final GangliaReporter reporter = provider.get();
        assertNotNull(reporter);
    }
}