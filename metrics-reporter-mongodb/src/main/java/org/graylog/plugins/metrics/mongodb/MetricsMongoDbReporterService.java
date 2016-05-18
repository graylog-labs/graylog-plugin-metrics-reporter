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
import com.google.common.util.concurrent.AbstractIdleService;
import io.github.aparnachaudhary.metrics.MongoDBReporter;

import javax.inject.Inject;

import static java.util.Objects.requireNonNull;

public class MetricsMongoDbReporterService extends AbstractIdleService {
    private final MetricsMongoDbReporterConfiguration configuration;
    private final MongoDBReporter mongoDBReporter;

    @Inject
    public MetricsMongoDbReporterService(MongoDBReporter mongoDBReporter, MetricsMongoDbReporterConfiguration configuration) {
        this.mongoDBReporter = requireNonNull(mongoDBReporter);
        this.configuration = requireNonNull(configuration);
    }

    @Override
    protected void startUp() throws Exception {
        if (configuration.isEnabled()) {
            final Duration reportInterval = configuration.getReportInterval();
            mongoDBReporter.start(reportInterval.getQuantity(), reportInterval.getUnit());
        }
    }

    @Override
    protected void shutDown() throws Exception {
        if (configuration.isEnabled()) {
            mongoDBReporter.stop();
        }
    }
}
