/**
 * This file is part of Graylog Metrics Elasticsearch Reporter Plugin.
 *
 * Graylog Metrics Elasticsearch Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics Elasticsearch Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics Elasticsearch Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.elasticsearch;

import com.github.joschi.jadconfig.util.Duration;
import org.elasticsearch.metrics.ElasticsearchReporter;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class MetricsElasticsearchReporterServiceTest {
    @Rule
    public final MockitoRule mockitoRule = MockitoJUnit.rule();
    @Mock
    private ElasticsearchReporter elasticsearchReporter;
    private final MetricsElasticsearchReporterConfiguration configuration = new MetricsElasticsearchReporterConfiguration() {
        @Override
        public boolean isEnabled() {
            return true;
        }
    };

    @Test
    public void serviceStartsJmxReporterIfEnabled() throws Exception {
        assumeTrue(configuration.isEnabled());
        final MetricsElasticsearchReporterService service = new MetricsElasticsearchReporterService(elasticsearchReporter, configuration);
        service.startAsync().awaitRunning();
        final Duration reportInterval = configuration.getReportInterval();
        verify(elasticsearchReporter).start(reportInterval.getQuantity(), reportInterval.getUnit());
    }

    @Test
    public void serviceDoesNotStartJmxReporterIfDisabled() throws Exception {
        final MetricsElasticsearchReporterConfiguration configuration = new MetricsElasticsearchReporterConfiguration();
        assumeFalse(configuration.isEnabled());
        final MetricsElasticsearchReporterService service = new MetricsElasticsearchReporterService(elasticsearchReporter, configuration);
        service.startAsync().awaitRunning();
        final Duration reportInterval = configuration.getReportInterval();
        verify(elasticsearchReporter, never()).start(reportInterval.getQuantity(), reportInterval.getUnit());
    }

    @Test
    public void serviceStopsJmxReporterIfEnabled() throws Exception {
        assumeTrue(configuration.isEnabled());
        final MetricsElasticsearchReporterService service = new MetricsElasticsearchReporterService(elasticsearchReporter, configuration);
        service.startAsync().awaitRunning();
        service.stopAsync().awaitTerminated();
        verify(elasticsearchReporter).stop();
    }

    @Test
    public void serviceDoesNotStopJmxReporterIfDisabled() throws Exception {
        final MetricsElasticsearchReporterConfiguration configuration = new MetricsElasticsearchReporterConfiguration();
        assumeFalse(configuration.isEnabled());
        final MetricsElasticsearchReporterService service = new MetricsElasticsearchReporterService(elasticsearchReporter, configuration);
        service.startAsync().awaitRunning();
        service.stopAsync().awaitTerminated();
        verify(elasticsearchReporter, never()).stop();
    }
}