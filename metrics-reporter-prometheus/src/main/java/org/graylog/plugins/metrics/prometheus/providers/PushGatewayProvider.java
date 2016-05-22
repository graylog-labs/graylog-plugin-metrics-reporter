/**
 * This file is part of Graylog Metrics Prometheus Reporter Plugin.
 *
 * Graylog Metrics Prometheus Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics Prometheus Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics Prometheus Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.prometheus.providers;

import com.google.common.net.HostAndPort;
import io.prometheus.client.exporter.PushGateway;
import org.graylog.plugins.metrics.prometheus.MetricsPrometheusReporterConfiguration;

import javax.inject.Inject;
import javax.inject.Provider;

import static java.util.Objects.requireNonNull;

public class PushGatewayProvider implements Provider<PushGateway> {
    private final MetricsPrometheusReporterConfiguration configuration;

    @Inject
    public PushGatewayProvider(MetricsPrometheusReporterConfiguration configuration) {
        this.configuration = requireNonNull(configuration);
    }

    @Override
    public PushGateway get() {
        final HostAndPort address = configuration.getAddress().withDefaultPort(9091);

        return new PushGateway(address.toString());
    }
}
