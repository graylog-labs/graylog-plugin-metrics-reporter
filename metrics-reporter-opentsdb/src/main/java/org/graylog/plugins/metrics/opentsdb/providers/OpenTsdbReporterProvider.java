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

import com.codahale.metrics.MetricRegistry;
import com.github.sps.metrics.OpenTsdbReporter;
import com.github.sps.metrics.opentsdb.OpenTsdb;
import org.graylog.plugins.metrics.core.RegexMetricFilter;
import org.graylog.plugins.metrics.opentsdb.MetricsOpenTsdbReporterConfiguration;

import javax.inject.Inject;
import javax.inject.Provider;

import static java.util.Objects.requireNonNull;

public class OpenTsdbReporterProvider implements Provider<OpenTsdbReporter> {
    private final MetricsOpenTsdbReporterConfiguration configuration;
    private final OpenTsdb openTsdb;
    private final MetricRegistry metricRegistry;

    @Inject
    public OpenTsdbReporterProvider(MetricsOpenTsdbReporterConfiguration configuration,
                                    OpenTsdb openTsdb,
                                    MetricRegistry metricRegistry) {
        this.configuration = requireNonNull(configuration, "configuration");
        this.metricRegistry = requireNonNull(metricRegistry, "metricRegistry");
        this.openTsdb = requireNonNull(openTsdb, "openTsdb");
    }

    @Override
    public OpenTsdbReporter get() {
        return OpenTsdbReporter.forRegistry(metricRegistry)
                .prefixedWith(configuration.getPrefix())
                .convertDurationsTo(configuration.getUnitDurations())
                .convertRatesTo(configuration.getUnitRates())
                .withBatchSize(configuration.getBatchSize())
                .withCounterGaugeDecorations(configuration.isCounterGaugeDecorations())
                .withTags(configuration.getTags())
                .filter(new RegexMetricFilter(configuration.getIncludeMetrics()))
                .build(openTsdb);
    }
}
