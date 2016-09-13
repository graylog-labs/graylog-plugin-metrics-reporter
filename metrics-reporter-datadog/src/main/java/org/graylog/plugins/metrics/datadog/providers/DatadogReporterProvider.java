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

import com.codahale.metrics.MetricRegistry;
import com.google.common.base.Throwables;
import org.coursera.metrics.datadog.DatadogReporter;
import org.coursera.metrics.datadog.transport.Transport;
import org.graylog.plugins.metrics.core.RegexMetricFilter;
import org.graylog.plugins.metrics.datadog.MetricsDatadogReporterConfiguration;

import javax.inject.Inject;
import javax.inject.Provider;
import java.io.IOException;

import static java.util.Objects.requireNonNull;

public class DatadogReporterProvider implements Provider<DatadogReporter> {
    private final MetricsDatadogReporterConfiguration configuration;
    private final Transport transport;
    private final MetricRegistry metricRegistry;

    @Inject
    public DatadogReporterProvider(MetricsDatadogReporterConfiguration configuration,
                                   Transport transport,
                                   MetricRegistry metricRegistry) {
        this.configuration = requireNonNull(configuration);
        this.transport = requireNonNull(transport);
        this.metricRegistry = requireNonNull(metricRegistry);
    }

    @Override
    public DatadogReporter get() {
        final DatadogReporter.Builder builder = DatadogReporter.forRegistry(metricRegistry)
                .withTransport(transport)
                .withPrefix(configuration.getPrefix())
                .convertRatesTo(configuration.getUnitRates())
                .convertDurationsTo(configuration.getUnitDurations())
                .filter(new RegexMetricFilter(configuration.getIncludeMetrics()));

        if (configuration.isEC2Instance()) {
            try {
                builder.withEC2Host();
            } catch (IOException e) {
                throw Throwables.propagate(e);
            }
        } else {
            builder.withHost(configuration.getHostname());
        }

        return builder.build();
    }
}
