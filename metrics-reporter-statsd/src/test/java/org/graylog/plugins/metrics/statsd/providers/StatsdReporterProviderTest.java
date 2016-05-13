/**
 * This file is part of Graylog Metrics statsd Reporter Plugin.
 *
 * Graylog Metrics statsd Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics statsd Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics statsd Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.statsd.providers;

import com.basistech.metrics.reporting.Statsd;
import com.basistech.metrics.reporting.StatsdReporter;
import com.codahale.metrics.MetricRegistry;
import org.graylog.plugins.metrics.statsd.MetricsStatsdReporterConfiguration;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class StatsdReporterProviderTest {
    @Test
    public void get() throws Exception {
        final MetricsStatsdReporterConfiguration configuration = new MetricsStatsdReporterConfiguration();
        final Statsd statsd = new Statsd("127.0.0.1", 8125);
        final StatsdReporterProvider provider = new StatsdReporterProvider(configuration, statsd, new MetricRegistry());
        final StatsdReporter reporter = provider.get();
        assertNotNull(reporter);
    }
}