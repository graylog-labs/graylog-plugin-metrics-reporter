/**
 * This file is part of Graylog Metrics InfluxDB Reporter Plugin.
 *
 * Graylog Metrics InfluxDB Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics InfluxDB Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics InfluxDB Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.influxdb.providers;

import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.izettle.metrics.influxdb.InfluxDbReporter;
import com.izettle.metrics.influxdb.InfluxDbSender;
import org.graylog.plugins.metrics.influxdb.MetricsInfluxDbReporterConfiguration;

import javax.inject.Inject;
import javax.inject.Provider;

import static java.util.Objects.requireNonNull;

public class InfluxDbReporterProvider implements Provider<InfluxDbReporter> {
    private final MetricsInfluxDbReporterConfiguration configuration;
    private final InfluxDbSender influxDbSender;
    private final MetricRegistry metricRegistry;

    @Inject
    public InfluxDbReporterProvider(MetricsInfluxDbReporterConfiguration configuration,
                                    InfluxDbSender influxDbSender,
                                    MetricRegistry metricRegistry) {
        this.configuration = requireNonNull(configuration);
        this.influxDbSender = requireNonNull(influxDbSender);
        this.metricRegistry = requireNonNull(metricRegistry);
    }

    @Override
    public InfluxDbReporter get() {
        return InfluxDbReporter.forRegistry(metricRegistry)
                .withTags(configuration.getTags())
                .groupGauges(configuration.isGroupGauges())
                .skipIdleMetrics(configuration.isSkipIdleMetrics())
                .convertDurationsTo(configuration.getUnitDurations())
                .convertRatesTo(configuration.getUnitRates())
                .filter(MetricFilter.ALL)
                .build(influxDbSender);
    }
}
