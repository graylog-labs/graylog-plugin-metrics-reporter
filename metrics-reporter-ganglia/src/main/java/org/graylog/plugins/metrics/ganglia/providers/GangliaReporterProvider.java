/**
 * This file is part of Graylog Metrics Ganglia Reporter Plugin.
 *
 * Graylog Metrics Ganglia Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics Ganglia Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics Ganglia Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.ganglia.providers;

import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ganglia.GangliaReporter;
import info.ganglia.gmetric4j.gmetric.GMetric;
import org.graylog.plugins.metrics.ganglia.MetricsGangliaReporterConfiguration;

import javax.inject.Inject;
import javax.inject.Provider;

import static java.util.Objects.requireNonNull;

public class GangliaReporterProvider implements Provider<GangliaReporter> {
    private final MetricsGangliaReporterConfiguration configuration;
    private final GMetric gMetric;
    private final MetricRegistry metricRegistry;

    @Inject
    public GangliaReporterProvider(MetricsGangliaReporterConfiguration configuration,
                                   GMetric gMetric,
                                   MetricRegistry metricRegistry) {
        this.configuration = requireNonNull(configuration);
        this.gMetric = requireNonNull(gMetric);
        this.metricRegistry = requireNonNull(metricRegistry);
    }

    @Override
    public GangliaReporter get() {
        return GangliaReporter.forRegistry(metricRegistry)
                .prefixedWith(configuration.getPrefix())
                .convertRatesTo(configuration.getUnitRates())
                .convertDurationsTo(configuration.getUnitDurations())
                .withDMax(configuration.getDMax())
                .withTMax(configuration.getTMax())
                .filter(MetricFilter.ALL)
                .build(gMetric);
    }
}
