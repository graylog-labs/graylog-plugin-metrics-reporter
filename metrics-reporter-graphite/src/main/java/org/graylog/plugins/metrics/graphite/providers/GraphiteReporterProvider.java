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

import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.GraphiteReporter;
import com.codahale.metrics.graphite.GraphiteSender;
import org.graylog.plugins.metrics.graphite.MetricsGraphiteReporterConfiguration;

import javax.inject.Inject;
import javax.inject.Provider;

import static java.util.Objects.requireNonNull;

public class GraphiteReporterProvider implements Provider<GraphiteReporter> {
    private final MetricsGraphiteReporterConfiguration configuration;
    private final GraphiteSender graphiteSender;
    private final MetricRegistry metricRegistry;

    @Inject
    public GraphiteReporterProvider(MetricsGraphiteReporterConfiguration configuration,
                                    GraphiteSender graphiteSender,
                                    MetricRegistry metricRegistry) {
        this.configuration = requireNonNull(configuration);
        this.graphiteSender = requireNonNull(graphiteSender);
        this.metricRegistry = requireNonNull(metricRegistry);
    }

    @Override
    public GraphiteReporter get() {
        return GraphiteReporter.forRegistry(metricRegistry)
                .prefixedWith(configuration.getPrefix())
                .convertRatesTo(configuration.getUnitRates())
                .convertDurationsTo(configuration.getUnitDurations())
                .filter(MetricFilter.ALL)
                .build(graphiteSender);
    }
}
