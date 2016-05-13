/**
 * This file is part of Graylog Metrics SLF4J Reporter Plugin.
 *
 * Graylog Metrics SLF4J Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics SLF4J Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics SLF4J Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.slf4j.providers;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Slf4jReporter;
import org.graylog.plugins.metrics.slf4j.MetricsSlf4jReporterConfiguration;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class Slf4jReporterProviderTest {
    @Test
    public void get() throws Exception {
        final Slf4jReporterProvider provider = new Slf4jReporterProvider(new MetricsSlf4jReporterConfiguration(), new MetricRegistry());
        final Slf4jReporter reporter = provider.get();
        assertNotNull(reporter);
    }
}