/**
 * This file is part of Graylog Metrics InfluxDB Reporter Plugin.
 *
 * Graylog Metrics InfluxDB Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics InfluxDB Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics InfluxDB Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.influxdb.providers;

import com.codahale.metrics.MetricRegistry;
import com.izettle.metrics.influxdb.InfluxDbHttpSender;
import com.izettle.metrics.influxdb.InfluxDbReporter;
import com.izettle.metrics.influxdb.InfluxDbSender;
import org.graylog.plugins.metrics.influxdb.MetricsInfluxDbReporterConfiguration;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;

public class InfluxDbReporterProviderTest {
    @Test
    public void get() throws Exception {
        final MetricsInfluxDbReporterConfiguration configuration = new MetricsInfluxDbReporterConfiguration();
        final InfluxDbSender influxDbSender = new InfluxDbHttpSender("http", "localhost", 1234, "database", null, TimeUnit.SECONDS, 5000, 5000, "");
        final InfluxDbReporterProvider provider = new InfluxDbReporterProvider(configuration, influxDbSender, new MetricRegistry());
        final InfluxDbReporter reporter = provider.get();
        assertNotNull(reporter);
    }
}