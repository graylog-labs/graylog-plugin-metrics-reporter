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
package org.graylog.plugins.metrics.elasticsearch.providers;

import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.google.common.base.Throwables;
import com.google.common.primitives.Ints;
import org.elasticsearch.metrics.ElasticsearchReporter;
import org.graylog.plugins.metrics.elasticsearch.MetricsElasticsearchReporterConfiguration;

import javax.inject.Inject;
import javax.inject.Provider;
import java.io.IOException;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class ElasticsearchReporterProvider implements Provider<ElasticsearchReporter> {
    private final MetricsElasticsearchReporterConfiguration configuration;
    private final MetricRegistry metricRegistry;

    @Inject
    public ElasticsearchReporterProvider(MetricsElasticsearchReporterConfiguration configuration,
                                         MetricRegistry metricRegistry) {
        this.configuration = requireNonNull(configuration);
        this.metricRegistry = requireNonNull(metricRegistry);
    }

    @Override
    public ElasticsearchReporter get() {
        final List<String> hosts = configuration.getHosts();
        try {
            return ElasticsearchReporter.forRegistry(metricRegistry)
                    .hosts(hosts.toArray(new String[hosts.size()]))
                    .timeout(Ints.saturatedCast(configuration.getConnectTimeout().toMilliseconds()))
                    .index(configuration.getIndex())
                    .bulkSize(configuration.getBulkSize())
                    .timestampFieldname(configuration.getTimestampFieldname())
                    .indexDateFormat(configuration.getIndexDateFormat())
                    .additionalFields(configuration.getAdditionalFields())
                    .prefixedWith(configuration.getPrefix())
                    .convertDurationsTo(configuration.getUnitDurations())
                    .convertRatesTo(configuration.getUnitRates())
                    .filter(MetricFilter.ALL)
                    .build();
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }
}
