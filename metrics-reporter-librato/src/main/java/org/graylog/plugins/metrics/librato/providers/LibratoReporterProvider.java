/**
 * This file is part of Graylog Metrics Librato Reporter Plugin.
 *
 * Graylog Metrics Librato Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics Librato Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics Librato Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.librato.providers;

import com.codahale.metrics.MetricRegistry;
import com.github.joschi.jadconfig.util.Duration;
import com.librato.metrics.reporter.LibratoReporter;
import com.librato.metrics.reporter.ReporterBuilder;
import org.graylog.plugins.metrics.core.RegexMetricFilter;
import org.graylog.plugins.metrics.librato.MetricsLibratoReporterConfiguration;

import javax.inject.Inject;
import javax.inject.Provider;

import static java.util.Objects.requireNonNull;

public class LibratoReporterProvider implements Provider<LibratoReporter> {
    private final MetricsLibratoReporterConfiguration configuration;
    private final MetricRegistry metricRegistry;

    @Inject
    public LibratoReporterProvider(MetricsLibratoReporterConfiguration configuration, MetricRegistry metricRegistry) {
        this.configuration = requireNonNull(configuration);
        this.metricRegistry = requireNonNull(metricRegistry);
    }

    @Override
    public LibratoReporter get() {
        final Duration timeout = configuration.getTimeout();
        final ReporterBuilder builder = LibratoReporter.builder(metricRegistry, configuration.getUsername(), configuration.getToken())
                .setName(configuration.getName())
                .setPrefix(configuration.getPrefix())
                .setPrefixDelimiter(configuration.getPrefixDelimiter())
                .setTimeout(timeout.getQuantity(), timeout.getUnit())
                .setDeleteIdleStats(configuration.isDeleteIdleStats())
                .setOmitComplexGauges(configuration.isOmitComplexGauges())
                .setDurationUnit(configuration.getUnitDurations())
                .setRateUnit(configuration.getUnitRates())
                .setFilter(new RegexMetricFilter(configuration.getIncludeMetrics()))
                .setEnableLegacy(configuration.isEnableLegacy())
                .setEnableTagging(configuration.isEnableTagging());

        if (configuration.isEnableTagging()) {
            configuration.getTags().forEach(builder::addTag);
        }

        if (configuration.getSource() != null) {
            builder.setSource(configuration.getSource());
        }

        if (configuration.getSourceRegex() != null) {
            builder.setSourceRegex(configuration.getSourceRegex().pattern());
        }

        return builder.build();
    }
}
