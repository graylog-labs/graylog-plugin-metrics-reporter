/**
 * This file is part of Graylog Metrics Librato Reporter Plugin.
 *
 * Graylog Metrics Librato Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics Librato Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics Librato Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.librato.providers;

import com.codahale.metrics.MetricRegistry;
import com.librato.metrics.reporter.LibratoReporter;
import org.graylog.plugins.metrics.librato.MetricsLibratoReporterConfiguration;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class LibratoReporterProviderTest {
    @Test
    public void get() throws Exception {
        final MetricsLibratoReporterConfiguration configuration = new MetricsLibratoReporterConfiguration() {
            @Override
            public String getUsername() {
                return "username";
            }

            @Override
            public String getToken() {
                return "token";
            }
        };
        final LibratoReporterProvider provider = new LibratoReporterProvider(configuration, new MetricRegistry());
        final LibratoReporter reporter = provider.get();
        assertNotNull(reporter);
    }
}