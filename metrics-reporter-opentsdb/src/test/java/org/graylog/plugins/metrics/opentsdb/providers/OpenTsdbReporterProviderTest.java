/**
 * This file is part of Graylog Metrics OpenTSDB Reporter Plugin.
 *
 * Graylog Metrics OpenTSDB Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics OpenTSDB Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics OpenTSDB Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.opentsdb.providers;

import com.codahale.metrics.MetricRegistry;
import com.github.sps.metrics.OpenTsdbReporter;
import com.github.sps.metrics.opentsdb.OpenTsdb;
import org.graylog.plugins.metrics.opentsdb.MetricsOpenTsdbReporterConfiguration;
import org.graylog2.shared.bindings.GuiceInjectorHolder;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertNotNull;

public class OpenTsdbReporterProviderTest {
    public OpenTsdbReporterProviderTest() {
        GuiceInjectorHolder.createInjector(Collections.emptyList());
    }

    @Test
    public void get() throws Exception {
        final MetricsOpenTsdbReporterConfiguration configuration = new MetricsOpenTsdbReporterConfiguration();
        final OpenTsdb openTsdb = OpenTsdb.forService("http://localhost:4242/").create();
        final OpenTsdbReporterProvider provider = new OpenTsdbReporterProvider(configuration, openTsdb, new MetricRegistry());
        final OpenTsdbReporter reporter = provider.get();
        assertNotNull(reporter);
    }
}