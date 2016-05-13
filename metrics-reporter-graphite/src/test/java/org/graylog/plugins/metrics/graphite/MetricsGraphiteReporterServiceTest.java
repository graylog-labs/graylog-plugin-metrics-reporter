/**
 * This file is part of Graylog Metrics Graphite Reporter Plugin.
 *
 * Graylog Metrics Graphite Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics Graphite Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics Graphite Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.graphite;

import com.codahale.metrics.graphite.GraphiteReporter;
import com.github.joschi.jadconfig.util.Duration;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class MetricsGraphiteReporterServiceTest {
    @Rule
    public final MockitoRule mockitoRule = MockitoJUnit.rule();
    @Mock
    private GraphiteReporter graphiteReporter;

    @Test
    public void serviceStartsGraphiteReporterIfEnabled() throws Exception {
        final MetricsGraphiteReporterConfiguration configuration = new MetricsGraphiteReporterConfiguration() {
            @Override
            public boolean isEnabled() {
                return true;
            }
        };
        assumeTrue(configuration.isEnabled());
        final MetricsGraphiteReporterService service = new MetricsGraphiteReporterService(graphiteReporter, configuration);
        service.startAsync().awaitRunning();
        final Duration reportInterval = configuration.getReportInterval();
        verify(graphiteReporter).start(reportInterval.getQuantity(), reportInterval.getUnit());
    }

    @Test
    public void serviceDoesNotStartGraphiteReporterIfDisabled() throws Exception {
        final MetricsGraphiteReporterConfiguration configuration = new MetricsGraphiteReporterConfiguration();
        assumeFalse(configuration.isEnabled());
        final MetricsGraphiteReporterService service = new MetricsGraphiteReporterService(graphiteReporter, configuration);
        service.startAsync().awaitRunning();
        final Duration reportInterval = configuration.getReportInterval();
        verify(graphiteReporter, never()).start(reportInterval.getQuantity(), reportInterval.getUnit());
    }

    @Test
    public void serviceStopsGraphiteReporterIfEnabled() throws Exception {
        final MetricsGraphiteReporterConfiguration configuration = new MetricsGraphiteReporterConfiguration() {
            @Override
            public boolean isEnabled() {
                return true;
            }
        };
        assumeTrue(configuration.isEnabled());
        final MetricsGraphiteReporterService service = new MetricsGraphiteReporterService(graphiteReporter, configuration);
        service.startAsync().awaitRunning();
        service.stopAsync().awaitTerminated();
        verify(graphiteReporter).stop();
    }

    @Test
    public void serviceDoesNotStopGraphiteReporterIfDisabled() throws Exception {
        final MetricsGraphiteReporterConfiguration configuration = new MetricsGraphiteReporterConfiguration();
        assumeFalse(configuration.isEnabled());
        final MetricsGraphiteReporterService service = new MetricsGraphiteReporterService(graphiteReporter, configuration);
        service.startAsync().awaitRunning();
        service.stopAsync().awaitTerminated();
        verify(graphiteReporter, never()).stop();
    }
}