/**
 * This file is part of Graylog Metrics statsd Reporter Plugin.
 *
 * Graylog Metrics statsd Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics statsd Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics statsd Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.statsd.providers;

import com.basistech.metrics.reporting.Statsd;
import com.google.common.net.HostAndPort;
import org.graylog.plugins.metrics.statsd.MetricsStatsdReporterConfiguration;

import javax.inject.Inject;
import javax.inject.Provider;

import static java.util.Objects.requireNonNull;

public class StatsdProvider implements Provider<Statsd> {
    private final MetricsStatsdReporterConfiguration configuration;

    @Inject
    public StatsdProvider(MetricsStatsdReporterConfiguration configuration) {
        this.configuration = requireNonNull(configuration);
    }

    @Override
    public Statsd get() {
        final HostAndPort address = configuration.getAddress();
        return new Statsd(address.getHostText(), address.getPortOrDefault(8125));
    }
}
