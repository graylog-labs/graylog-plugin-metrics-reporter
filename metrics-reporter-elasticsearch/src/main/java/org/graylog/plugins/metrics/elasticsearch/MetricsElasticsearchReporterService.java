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
import com.google.common.util.concurrent.AbstractIdleService;
import org.elasticsearch.metrics.ElasticsearchReporter;

import javax.inject.Inject;

import static java.util.Objects.requireNonNull;

public class MetricsElasticsearchReporterService extends AbstractIdleService {
    private final MetricsElasticsearchReporterConfiguration configuration;
    private final ElasticsearchReporter elasticsearchReporter;

    @Inject
    public MetricsElasticsearchReporterService(ElasticsearchReporter elasticsearchReporter, MetricsElasticsearchReporterConfiguration configuration) {
        this.elasticsearchReporter = requireNonNull(elasticsearchReporter);
        this.configuration = requireNonNull(configuration);
    }

    @Override
    protected void startUp() throws Exception {
        if (configuration.isEnabled()) {
            final Duration reportInterval = configuration.getReportInterval();
            elasticsearchReporter.start(reportInterval.getQuantity(), reportInterval.getUnit());
        }
    }

    @Override
    protected void shutDown() throws Exception {
        if (configuration.isEnabled()) {
            elasticsearchReporter.stop();
        }
    }
}
