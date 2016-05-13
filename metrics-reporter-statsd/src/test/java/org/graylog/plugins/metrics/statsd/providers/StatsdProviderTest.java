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
import org.graylog.plugins.metrics.statsd.MetricsStatsdReporterConfiguration;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class StatsdProviderTest {
    @Test
    public void get() throws Exception {
        final StatsdProvider provider = new StatsdProvider(new MetricsStatsdReporterConfiguration());
        final Statsd statsd = provider.get();
        assertNotNull(statsd);
    }
}