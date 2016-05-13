/**
 * This file is part of Graylog Metrics Console Reporter Plugin.
 *
 * Graylog Metrics Console Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics Console Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics Console Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.console.providers;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import org.graylog.plugins.metrics.console.MetricsConsoleReporterConfiguration;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ConsoleReporterProviderTest {
    @Test
    public void get() throws Exception {
        final ConsoleReporterProvider provider = new ConsoleReporterProvider(new MetricsConsoleReporterConfiguration(), new MetricRegistry());
        final ConsoleReporter reporter = provider.get();
        assertNotNull(reporter);
    }
}