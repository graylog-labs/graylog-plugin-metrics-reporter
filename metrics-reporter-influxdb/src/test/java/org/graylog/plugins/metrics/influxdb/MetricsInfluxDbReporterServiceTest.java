/**
 * This file is part of Graylog Metrics InfluxDB Reporter Plugin.
 *
 * Graylog Metrics InfluxDB Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics InfluxDB Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics InfluxDB Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.influxdb;

import com.github.joschi.jadconfig.util.Duration;
import com.izettle.metrics.influxdb.InfluxDbReporter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PrepareForTest(InfluxDbReporter.class)
public class MetricsInfluxDbReporterServiceTest {
    @Rule
    public final MockitoRule mockitoRule = MockitoJUnit.rule();
    private InfluxDbReporter influxDbReporter;
    private final MetricsInfluxDbReporterConfiguration configuration = new MetricsInfluxDbReporterConfiguration() {
        @Override
        public boolean isEnabled() {
            return true;
        }
    };

    @Before
    public void setUp() {
        this.influxDbReporter = PowerMockito.mock(InfluxDbReporter.class);
    }

    @Test
    public void serviceStartsJmxReporterIfEnabled() throws Exception {
        assumeTrue(configuration.isEnabled());
        final MetricsInfluxDbReporterService service = new MetricsInfluxDbReporterService(influxDbReporter, configuration);
        service.startAsync().awaitRunning();
        final Duration reportInterval = configuration.getReportInterval();
        verify(influxDbReporter).start(reportInterval.getQuantity(), reportInterval.getUnit());
    }

    @Test
    public void serviceDoesNotStartJmxReporterIfDisabled() throws Exception {
        final MetricsInfluxDbReporterConfiguration configuration = new MetricsInfluxDbReporterConfiguration();
        assumeFalse(configuration.isEnabled());
        final MetricsInfluxDbReporterService service = new MetricsInfluxDbReporterService(influxDbReporter, configuration);
        service.startAsync().awaitRunning();
        final Duration reportInterval = configuration.getReportInterval();
        verify(influxDbReporter, never()).start(reportInterval.getQuantity(), reportInterval.getUnit());
    }

    @Test
    public void serviceStopsJmxReporterIfEnabled() throws Exception {
        assumeTrue(configuration.isEnabled());
        final MetricsInfluxDbReporterService service = new MetricsInfluxDbReporterService(influxDbReporter, configuration);
        service.startAsync().awaitRunning();
        service.stopAsync().awaitTerminated();
        verify(influxDbReporter).stop();
    }

    @Test
    public void serviceDoesNotStopJmxReporterIfDisabled() throws Exception {
        final MetricsInfluxDbReporterConfiguration configuration = new MetricsInfluxDbReporterConfiguration();
        assumeFalse(configuration.isEnabled());
        final MetricsInfluxDbReporterService service = new MetricsInfluxDbReporterService(influxDbReporter, configuration);
        service.startAsync().awaitRunning();
        service.stopAsync().awaitTerminated();
        verify(influxDbReporter, never()).stop();
    }
}