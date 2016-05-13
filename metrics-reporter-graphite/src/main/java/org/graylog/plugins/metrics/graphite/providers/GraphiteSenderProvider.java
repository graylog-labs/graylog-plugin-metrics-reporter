/**
 * This file is part of Graylog Metrics Graphite Reporter Plugin.
 *
 * Graylog Metrics Graphite Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics Graphite Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics Graphite Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.graphite.providers;

import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteSender;
import com.codahale.metrics.graphite.GraphiteUDP;
import com.codahale.metrics.graphite.PickledGraphite;
import org.graylog.plugins.metrics.graphite.MetricsGraphiteReporterConfiguration;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.net.SocketFactory;

import static java.util.Objects.requireNonNull;

public class GraphiteSenderProvider implements Provider<GraphiteSender> {
    private final MetricsGraphiteReporterConfiguration configuration;

    @Inject
    public GraphiteSenderProvider(MetricsGraphiteReporterConfiguration configuration) {
        this.configuration = requireNonNull(configuration);
    }

    @Override
    public GraphiteSender get() {
        switch (configuration.getProtocol()) {
            case PICKLE:
                return new PickledGraphite(
                        configuration.getAddress(),
                        SocketFactory.getDefault(),
                        configuration.getCharset(),
                        configuration.getPickleBatchSize());
            case TCP:
                return new Graphite(configuration.getAddress(), SocketFactory.getDefault(), configuration.getCharset());
            case UDP:
                return new GraphiteUDP(configuration.getAddress());
            default:
                throw new IllegalArgumentException("Unknown Graphite protocol \"" + configuration.getProtocol() + "\"");
        }
    }
}
