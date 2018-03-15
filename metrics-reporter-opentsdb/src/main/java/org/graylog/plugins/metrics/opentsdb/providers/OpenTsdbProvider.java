/**
 * This file is part of Graylog Metrics OpenTSDB Reporter Plugin.
 *
 * Graylog Metrics OpenTSDB Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics OpenTSDB Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics OpenTSDB Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.opentsdb.providers;

import com.github.sps.metrics.opentsdb.OpenTsdb;
import com.github.sps.metrics.opentsdb.OpenTsdbTelnet;
import com.google.common.net.HostAndPort;
import org.graylog.plugins.metrics.opentsdb.MetricsOpenTsdbReporterConfiguration;
import org.graylog.plugins.metrics.opentsdb.OpenTsdbProtocol;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import static java.util.Objects.requireNonNull;

@Singleton
public class OpenTsdbProvider implements Provider<OpenTsdb> {
    private final MetricsOpenTsdbReporterConfiguration configuration;

    @Inject
    public OpenTsdbProvider(MetricsOpenTsdbReporterConfiguration configuration) {
        this.configuration = requireNonNull(configuration, "configuration");
    }

    @Override
    public OpenTsdb get() {
        final OpenTsdbProtocol protocol = configuration.getProtocol();
        switch (protocol) {
            case HTTP:
                return OpenTsdb.forService(configuration.getHttpBaseUrl().toASCIIString())
                        .withConnectTimeout(configuration.getHttpConnectTimeout())
                        .withReadTimeout(configuration.getHttpReadTimeout())
                        .withGzipEnabled(configuration.isHttpEnableGzip())
                        .create();
            case TELNET:
                final HostAndPort address = configuration.getTelnetAddress();
                final String host = address.getHost();
                final int port = address.getPortOrDefault(MetricsOpenTsdbReporterConfiguration.DEFAULT_PORT);

                return OpenTsdbTelnet.forService(host, port).create();
            default:
                throw new IllegalStateException("Invalid OpenTSDB protocol: " + protocol);
        }
    }
}
