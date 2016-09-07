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
import com.google.common.net.HostAndPort;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import io.github.aparnachaudhary.metrics.MongoDBReporter;
import org.graylog.plugins.metrics.core.RegexMetricFilter;
import org.graylog.plugins.metrics.mongodb.MetricsMongoDbReporterConfiguration;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class MongoDBReporterProvider implements Provider<MongoDBReporter> {
    private final MetricsMongoDbReporterConfiguration configuration;
    private final MetricRegistry metricRegistry;

    @Inject
    public MongoDBReporterProvider(MetricsMongoDbReporterConfiguration configuration,
                                   MetricRegistry metricRegistry) {
        this.configuration = requireNonNull(configuration);
        this.metricRegistry = requireNonNull(metricRegistry);
    }

    @Override
    public MongoDBReporter get() {
        final MongoClientURI mongoClientURI = configuration.getUri();
        final MongoCredential credentials = mongoClientURI.getCredentials();
        return MongoDBReporter.forRegistry(metricRegistry)
                .serverAddresses(extractServerAddresses(mongoClientURI))
                .mongoCredentials(credentials == null ? new MongoCredential[0] : new MongoCredential[]{credentials})
                .mongoClientOptions(mongoClientURI.getOptions())
                .withDatabaseName(mongoClientURI.getDatabase())
                .additionalFields(configuration.getAdditionalFields())
                .convertDurationsTo(configuration.getUnitDurations())
                .convertRatesTo(configuration.getUnitRates())
                .filter(new RegexMetricFilter(configuration.getIncludeMetrics()))
                .build();
    }

    private ServerAddress[] extractServerAddresses(MongoClientURI mongoClientURI) {
        final List<String> hosts = mongoClientURI.getHosts();
        final List<ServerAddress> serverAddresses = new ArrayList<>(hosts.size());
        for (String host : hosts) {
            final HostAndPort hostAndPort = HostAndPort.fromString(host).withDefaultPort(ServerAddress.defaultPort());
            final ServerAddress serverAddress = new ServerAddress(hostAndPort.getHostText(), hostAndPort.getPort());
            serverAddresses.add(serverAddress);
        }
        return serverAddresses.toArray(new ServerAddress[serverAddresses.size()]);
    }
}
