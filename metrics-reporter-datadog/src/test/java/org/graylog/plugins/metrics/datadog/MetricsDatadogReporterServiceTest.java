/**
 * This file is part of Graylog Metrics Datadog Reporter Plugin.
 *
 * Graylog Metrics Datadog Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics Datadog Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics Datadog Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.datadog;

import com.github.joschi.jadconfig.util.Duration;
import org.coursera.metrics.datadog.DatadogReporter;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class MetricsDatadogReporterServiceTest {
    @Rule
    public final MockitoRule mockitoRule = MockitoJUnit.rule();
    @Mock
    private DatadogReporter datadogReporter;

    @Test
    public void serviceStartsGraphiteReporterIfEnabled() throws Exception {
        final MetricsDatadogReporterConfiguration configuration = new MetricsDatadogReporterConfiguration() {
            @Override
            public boolean isEnabled() {
                return true;
            }
        };
        assumeTrue(configuration.isEnabled());
        final MetricsDatadogReporterService service = new MetricsDatadogReporterService(datadogReporter, configuration);
        service.startAsync().awaitRunning();
        final Duration reportInterval = configuration.getReportInterval();
        verify(datadogReporter).start(reportInterval.getQuantity(), reportInterval.getUnit());
    }

    @Test
    public void serviceDoesNotStartGraphiteReporterIfDisabled() throws Exception {
        final MetricsDatadogReporterConfiguration configuration = new MetricsDatadogReporterConfiguration();
        assumeFalse(configuration.isEnabled());
        final MetricsDatadogReporterService service = new MetricsDatadogReporterService(datadogReporter, configuration);
        service.startAsync().awaitRunning();
        final Duration reportInterval = configuration.getReportInterval();
        verify(datadogReporter, never()).start(reportInterval.getQuantity(), reportInterval.getUnit());
    }

    @Test
    public void serviceStopsGraphiteReporterIfEnabled() throws Exception {
        final MetricsDatadogReporterConfiguration configuration = new MetricsDatadogReporterConfiguration() {
            @Override
            public boolean isEnabled() {
                return true;
            }
        };
        assumeTrue(configuration.isEnabled());
        final MetricsDatadogReporterService service = new MetricsDatadogReporterService(datadogReporter, configuration);
        service.startAsync().awaitRunning();
        service.stopAsync().awaitTerminated();
        verify(datadogReporter).stop();
    }

    @Test
    public void serviceDoesNotStopGraphiteReporterIfDisabled() throws Exception {
        final MetricsDatadogReporterConfiguration configuration = new MetricsDatadogReporterConfiguration();
        assumeFalse(configuration.isEnabled());
        final MetricsDatadogReporterService service = new MetricsDatadogReporterService(datadogReporter, configuration);
        service.startAsync().awaitRunning();
        service.stopAsync().awaitTerminated();
        verify(datadogReporter, never()).stop();
    }
}