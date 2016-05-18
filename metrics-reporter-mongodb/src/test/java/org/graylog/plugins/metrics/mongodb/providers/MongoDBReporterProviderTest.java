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
package org.graylog.plugins.metrics.mongodb.providers;

import com.codahale.metrics.MetricRegistry;
import com.mongodb.MongoClientURI;
import io.github.aparnachaudhary.metrics.MongoDBReporter;
import org.graylog.plugins.metrics.mongodb.MetricsMongoDbReporterConfiguration;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class MongoDBReporterProviderTest {
    @Test
    public void get() throws Exception {
        final MetricsMongoDbReporterConfiguration configuration = new MetricsMongoDbReporterConfiguration() {
            @Override
            public MongoClientURI getUri() {
                return new MongoClientURI("mongodb://127.0.0.1/test");
            }
        };
        final MongoDBReporterProvider provider = new MongoDBReporterProvider(configuration, new MetricRegistry());
        final MongoDBReporter reporter = provider.get();
        assertNotNull(reporter);
    }
}