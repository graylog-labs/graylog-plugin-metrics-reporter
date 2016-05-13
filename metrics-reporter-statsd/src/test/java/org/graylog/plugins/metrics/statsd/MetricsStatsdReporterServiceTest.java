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
package org.graylog.plugins.metrics.statsd;

import com.basistech.metrics.reporting.StatsdReporter;
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

public class MetricsStatsdReporterServiceTest {
    @Rule
    public final MockitoRule mockitoRule = MockitoJUnit.rule();
    @Mock
    private StatsdReporter statsdReporter;
    private final MetricsStatsdReporterConfiguration configuration = new MetricsStatsdReporterConfiguration() {
        @Override
        public boolean isEnabled() {
            return true;
        }
    };

    @Test
    public void serviceStartsJmxReporterIfEnabled() throws Exception {
        assumeTrue(configuration.isEnabled());
        final MetricsStatsdReporterService service = new MetricsStatsdReporterService(statsdReporter, configuration);
        service.startAsync().awaitRunning();
        final Duration reportInterval = configuration.getReportInterval();
        verify(statsdReporter).start(reportInterval.getQuantity(), reportInterval.getUnit());
    }

    @Test
    public void serviceDoesNotStartJmxReporterIfDisabled() throws Exception {
        final MetricsStatsdReporterConfiguration configuration = new MetricsStatsdReporterConfiguration();
        assumeFalse(configuration.isEnabled());
        final MetricsStatsdReporterService service = new MetricsStatsdReporterService(statsdReporter, configuration);
        service.startAsync().awaitRunning();
        final Duration reportInterval = configuration.getReportInterval();
        verify(statsdReporter, never()).start(reportInterval.getQuantity(), reportInterval.getUnit());
    }

    @Test
    public void serviceStopsJmxReporterIfEnabled() throws Exception {
        assumeTrue(configuration.isEnabled());
        final MetricsStatsdReporterService service = new MetricsStatsdReporterService(statsdReporter, configuration);
        service.startAsync().awaitRunning();
        service.stopAsync().awaitTerminated();
        verify(statsdReporter).stop();
    }

    @Test
    public void serviceDoesNotStopJmxReporterIfDisabled() throws Exception {
        final MetricsStatsdReporterConfiguration configuration = new MetricsStatsdReporterConfiguration();
        assumeFalse(configuration.isEnabled());
        final MetricsStatsdReporterService service = new MetricsStatsdReporterService(statsdReporter, configuration);
        service.startAsync().awaitRunning();
        service.stopAsync().awaitTerminated();
        verify(statsdReporter, never()).stop();
    }
}