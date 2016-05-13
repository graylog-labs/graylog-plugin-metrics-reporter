/**
 * This file is part of Graylog Metrics JMX Reporter Plugin.
 *
 * Graylog Metrics JMX Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics JMX Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics JMX Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.jmx.providers;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import org.graylog.plugins.metrics.jmx.MetricsJmxReporterConfiguration;

import javax.inject.Inject;
import javax.inject.Provider;

import static java.util.Objects.requireNonNull;

public class JmxReporterProvider implements Provider<JmxReporter> {
    private final MetricsJmxReporterConfiguration configuration;
    private final MetricRegistry metricRegistry;

    @Inject
    public JmxReporterProvider(MetricsJmxReporterConfiguration configuration, MetricRegistry metricRegistry) {
        this.configuration = requireNonNull(configuration);
        this.metricRegistry = requireNonNull(metricRegistry);
    }

    @Override
    public JmxReporter get() {
        return JmxReporter.forRegistry(metricRegistry)
                .convertDurationsTo(configuration.getUnitDurations())
                .convertRatesTo(configuration.getUnitRates())
                .filter(MetricFilter.ALL)
                .build();
    }
}
