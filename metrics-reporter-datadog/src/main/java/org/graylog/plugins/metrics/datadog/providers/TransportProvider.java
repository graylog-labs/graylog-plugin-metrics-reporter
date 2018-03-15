/**
 * This file is part of Graylog Metrics Datadog Reporter Plugin.
 *
 * Graylog Metrics Datadog Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics Datadog Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics Datadog Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.datadog.providers;

import com.github.joschi.jadconfig.util.Duration;
import com.google.common.net.HostAndPort;
import com.google.common.primitives.Ints;
import org.coursera.metrics.datadog.transport.HttpTransport;
import org.coursera.metrics.datadog.transport.Transport;
import org.coursera.metrics.datadog.transport.UdpTransport;
import org.graylog.plugins.metrics.datadog.MetricsDatadogReporterConfiguration;

import javax.inject.Inject;
import javax.inject.Provider;

import static java.util.Objects.requireNonNull;

public class TransportProvider implements Provider<Transport> {
    private final MetricsDatadogReporterConfiguration configuration;

    @Inject
    public TransportProvider(MetricsDatadogReporterConfiguration configuration) {
        this.configuration = requireNonNull(configuration);
    }

    @Override
    public Transport get() {
        switch (configuration.getTransport()) {
            case HTTP: {
                final HostAndPort proxy = configuration.getHttpProxy();
                final Duration connectTimeout = configuration.getHttpConnectTimeout();
                final Duration socketTimeout = configuration.getHttpSocketTimeout();
                final HttpTransport.Builder builder = new HttpTransport.Builder()
                        .withApiKey(configuration.getApiKey())
                        .withConnectTimeout(Ints.saturatedCast(connectTimeout.toMilliseconds()))
                        .withSocketTimeout(Ints.saturatedCast(socketTimeout.toMilliseconds()));
                if (proxy != null) {
                    builder.withProxy(proxy.getHost(), proxy.getPortOrDefault(8080));
                }
                return builder.build();
            }
            case UDP: {
                final HostAndPort udpAddress = configuration.getUdpAddress();
                return new UdpTransport.Builder()
                        .withStatsdHost(udpAddress.getHost())
                        .withPort(udpAddress.getPortOrDefault(8125))
                        .withPrefix(configuration.getUdpPrefix())
                        .build();
            }
            default:
                throw new IllegalArgumentException("Unknown Datadog transport \"" + configuration.getTransport() + "\"");
        }
    }
}
