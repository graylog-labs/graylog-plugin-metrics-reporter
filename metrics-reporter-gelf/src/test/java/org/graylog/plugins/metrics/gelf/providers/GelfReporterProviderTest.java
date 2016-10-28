/**
 * This file is part of Graylog Metrics GELF Reporter Plugin.
 *
 * Graylog Metrics GELF Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics GELF Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics GELF Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.gelf.providers;

import com.codahale.metrics.MetricRegistry;
import org.graylog.metrics.GelfReporter;
import org.graylog.plugins.metrics.gelf.MetricsGelfReporterConfiguration;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class GelfReporterProviderTest {
    @Test
    public void get() throws Exception {
        final MetricsGelfReporterConfiguration configuration = new MetricsGelfReporterConfiguration();
        final GelfReporterProvider provider = new GelfReporterProvider(configuration, new MetricRegistry());
        final GelfReporter reporter = provider.get();
        assertNotNull(reporter);
    }
}