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

import com.codahale.metrics.MetricRegistry;
import org.elasticsearch.metrics.ElasticsearchReporter;
import org.graylog.plugins.metrics.elasticsearch.MetricsElasticsearchReporterConfiguration;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ElasticsearchReporterProviderTest {
    @Test
    public void get() throws Exception {
        final MetricsElasticsearchReporterConfiguration configuration = new MetricsElasticsearchReporterConfiguration();
        final ElasticsearchReporterProvider provider = new ElasticsearchReporterProvider(configuration, new MetricRegistry());
        final ElasticsearchReporter reporter = provider.get();
        assertNotNull(reporter);
    }
}