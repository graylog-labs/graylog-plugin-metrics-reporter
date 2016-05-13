/**
 * This file is part of Graylog Metrics JMX Reporter Plugin.
 *
 * Graylog Metrics JMX Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics JMX Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics JMX Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.jmx;

import com.codahale.metrics.JmxReporter;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class MetricsJmxReporterServiceTest {
    @Rule
    public final MockitoRule mockitoRule = MockitoJUnit.rule();
    @Mock
    private JmxReporter jmxReporter;
    private final MetricsJmxReporterConfiguration configuration = new MetricsJmxReporterConfiguration() {
        @Override
        public boolean isEnabled() {
            return true;
        }
    };

    @Test
    public void serviceStartsJmxReporterIfEnabled() throws Exception {
        assumeTrue(configuration.isEnabled());
        final MetricsJmxReporterService service = new MetricsJmxReporterService(jmxReporter, configuration);
        service.startAsync().awaitRunning();
        verify(jmxReporter).start();
    }

    @Test
    public void serviceDoesNotStartJmxReporterIfDisabled() throws Exception {
        final MetricsJmxReporterConfiguration configuration = new MetricsJmxReporterConfiguration();
        assumeFalse(configuration.isEnabled());
        final MetricsJmxReporterService service = new MetricsJmxReporterService(jmxReporter, configuration);
        service.startAsync().awaitRunning();
        verify(jmxReporter, never()).start();
    }

    @Test
    public void serviceStopsJmxReporterIfEnabled() throws Exception {
        assumeTrue(configuration.isEnabled());
        final MetricsJmxReporterService service = new MetricsJmxReporterService(jmxReporter, configuration);
        service.startAsync().awaitRunning();
        service.stopAsync().awaitTerminated();
        verify(jmxReporter).stop();
    }

    @Test
    public void serviceDoesNotStopJmxReporterIfDisabled() throws Exception {
        final MetricsJmxReporterConfiguration configuration = new MetricsJmxReporterConfiguration();
        assumeFalse(configuration.isEnabled());
        final MetricsJmxReporterService service = new MetricsJmxReporterService(jmxReporter, configuration);
        service.startAsync().awaitRunning();
        service.stopAsync().awaitTerminated();
        verify(jmxReporter, never()).stop();
    }
}