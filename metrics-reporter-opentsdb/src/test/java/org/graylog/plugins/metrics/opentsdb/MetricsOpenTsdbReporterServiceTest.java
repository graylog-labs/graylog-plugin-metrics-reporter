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
package org.graylog.plugins.metrics.opentsdb;

import com.github.joschi.jadconfig.util.Duration;
import com.github.sps.metrics.OpenTsdbReporter;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class MetricsOpenTsdbReporterServiceTest {
    @Rule
    public final MockitoRule mockitoRule = MockitoJUnit.rule();
    @Mock
    private OpenTsdbReporter reporter;
    private final MetricsOpenTsdbReporterConfiguration configuration = new MetricsOpenTsdbReporterConfiguration() {
        @Override
        public boolean isEnabled() {
            return true;
        }
    };

    @Test
    public void serviceStartsOpenTsdbReporterIfEnabled() throws Exception {
        assumeTrue(configuration.isEnabled());
        final MetricsOpenTsdbReporterService service = new MetricsOpenTsdbReporterService(reporter, configuration);
        service.startAsync().awaitRunning();
        final Duration reportInterval = configuration.getReportInterval();
        verify(reporter).start(reportInterval.getQuantity(), reportInterval.getUnit());
    }

    @Test
    public void serviceDoesNotStartOpenTsdbReporterIfDisabled() throws Exception {
        final MetricsOpenTsdbReporterConfiguration configuration = new MetricsOpenTsdbReporterConfiguration();
        assumeFalse(configuration.isEnabled());
        final MetricsOpenTsdbReporterService service = new MetricsOpenTsdbReporterService(reporter, configuration);
        service.startAsync().awaitRunning();
        final Duration reportInterval = configuration.getReportInterval();
        verify(reporter, never()).start(reportInterval.getQuantity(), reportInterval.getUnit());
    }

    @Test
    public void serviceStopsOpenTsdbReporterIfEnabled() throws Exception {
        assumeTrue(configuration.isEnabled());
        final MetricsOpenTsdbReporterService service = new MetricsOpenTsdbReporterService(reporter, configuration);
        service.startAsync().awaitRunning();
        service.stopAsync().awaitTerminated();
        verify(reporter).stop();
    }

    @Test
    public void serviceDoesNotStopOpenTsdbReporterIfDisabled() throws Exception {
        final MetricsOpenTsdbReporterConfiguration configuration = new MetricsOpenTsdbReporterConfiguration();
        assumeFalse(configuration.isEnabled());
        final MetricsOpenTsdbReporterService service = new MetricsOpenTsdbReporterService(reporter, configuration);
        service.startAsync().awaitRunning();
        service.stopAsync().awaitTerminated();
        verify(reporter, never()).stop();
    }
}