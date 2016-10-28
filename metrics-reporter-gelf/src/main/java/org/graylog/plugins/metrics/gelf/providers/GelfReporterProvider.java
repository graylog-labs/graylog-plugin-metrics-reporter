/**
 * This file is part of Graylog Metrics GELF Reporter Plugin.
 *
 * Graylog Metrics GELF Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics GELF Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics GELF Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.gelf.providers;

import com.codahale.metrics.MetricRegistry;
import com.google.common.primitives.Ints;
import org.graylog.metrics.GelfReporter;
import org.graylog.plugins.metrics.core.RegexMetricFilter;
import org.graylog.plugins.metrics.gelf.MetricsGelfReporterConfiguration;

import javax.inject.Inject;
import javax.inject.Provider;

import static java.util.Objects.requireNonNull;

public class GelfReporterProvider implements Provider<GelfReporter> {
    private final MetricsGelfReporterConfiguration configuration;
    private final MetricRegistry metricRegistry;

    @Inject
    public GelfReporterProvider(MetricsGelfReporterConfiguration configuration, MetricRegistry metricRegistry) {
        this.configuration = requireNonNull(configuration);
        this.metricRegistry = requireNonNull(metricRegistry);
    }

    @Override
    public GelfReporter get() {
        return GelfReporter.forRegistry(metricRegistry)
                .prefixedWith(configuration.getPrefix())
                .convertDurationsTo(configuration.getUnitDurations())
                .convertRatesTo(configuration.getUnitRates())
                .host(configuration.getHost())
                .transport(configuration.getTransport())
                .level(configuration.getLevel())
                .source(configuration.getSource())
                .tlsEnabled(configuration.isTlsEnabled())
                .tlsCertVerificationEnabled(configuration.isTlsCertVerificationEnabled())
                .tlsTrustCertChainFile(configuration.getTlsTrustCertChainFile())
                .tcpKeepAlive(configuration.isTcpKeepAlive())
                .tcpNoDelay(configuration.isTcpNoDelay())
                .queueSize(configuration.getQueueSize())
                .connectTimeout(Ints.saturatedCast(configuration.getConnectTimeout().toMilliseconds()))
                .reconnectDelay(Ints.saturatedCast(configuration.getReconnectDelay().toMilliseconds()))
                .sendBufferSize(configuration.getSendBufferSize())
                .maxInFlightSends(configuration.getMaxInFlightSends())
                .filter(new RegexMetricFilter(configuration.getIncludeMetrics()))
                .build();
    }
}
