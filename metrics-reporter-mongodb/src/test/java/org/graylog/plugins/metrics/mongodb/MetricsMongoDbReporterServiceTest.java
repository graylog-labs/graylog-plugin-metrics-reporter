/**
 * This file is part of Graylog Metrics MongoDB Reporter Plugin.
 *
 * Graylog Metrics MongoDB Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics MongoDB Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics MongoDB Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.mongodb;

import com.github.joschi.jadconfig.util.Duration;
import io.github.aparnachaudhary.metrics.MongoDBReporter;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class MetricsMongoDbReporterServiceTest {
    @Rule
    public final MockitoRule mockitoRule = MockitoJUnit.rule();
    @Mock
    private MongoDBReporter mongoDBReporter;
    private final MetricsMongoDbReporterConfiguration configuration = new MetricsMongoDbReporterConfiguration() {
        @Override
        public boolean isEnabled() {
            return true;
        }
    };

    @Test
    public void serviceStartsJmxReporterIfEnabled() throws Exception {
        assumeTrue(configuration.isEnabled());
        final MetricsMongoDbReporterService service = new MetricsMongoDbReporterService(mongoDBReporter, configuration);
        service.startAsync().awaitRunning();
        final Duration reportInterval = configuration.getReportInterval();
        verify(mongoDBReporter).start(reportInterval.getQuantity(), reportInterval.getUnit());
    }

    @Test
    public void serviceDoesNotStartJmxReporterIfDisabled() throws Exception {
        final MetricsMongoDbReporterConfiguration configuration = new MetricsMongoDbReporterConfiguration();
        assumeFalse(configuration.isEnabled());
        final MetricsMongoDbReporterService service = new MetricsMongoDbReporterService(mongoDBReporter, configuration);
        service.startAsync().awaitRunning();
        final Duration reportInterval = configuration.getReportInterval();
        verify(mongoDBReporter, never()).start(reportInterval.getQuantity(), reportInterval.getUnit());
    }

    @Test
    public void serviceStopsJmxReporterIfEnabled() throws Exception {
        assumeTrue(configuration.isEnabled());
        final MetricsMongoDbReporterService service = new MetricsMongoDbReporterService(mongoDBReporter, configuration);
        service.startAsync().awaitRunning();
        service.stopAsync().awaitTerminated();
        verify(mongoDBReporter).stop();
    }

    @Test
    public void serviceDoesNotStopJmxReporterIfDisabled() throws Exception {
        final MetricsMongoDbReporterConfiguration configuration = new MetricsMongoDbReporterConfiguration();
        assumeFalse(configuration.isEnabled());
        final MetricsMongoDbReporterService service = new MetricsMongoDbReporterService(mongoDBReporter, configuration);
        service.startAsync().awaitRunning();
        service.stopAsync().awaitTerminated();
        verify(mongoDBReporter, never()).stop();
    }
}