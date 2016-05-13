/**
 * This file is part of Graylog Metrics SLF4J Reporter Plugin.
 *
 * Graylog Metrics SLF4J Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics SLF4J Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics SLF4J Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.slf4j;

import com.codahale.metrics.Slf4jReporter;
import com.github.joschi.jadconfig.util.Duration;
import com.google.common.util.concurrent.AbstractIdleService;

import javax.inject.Inject;

import static java.util.Objects.requireNonNull;

public class MetricsSlf4jReporterService extends AbstractIdleService {
    private final MetricsSlf4jReporterConfiguration configuration;
    private final Slf4jReporter slf4jReporter;

    @Inject
    public MetricsSlf4jReporterService(Slf4jReporter slf4jReporter, MetricsSlf4jReporterConfiguration configuration) {
        this.slf4jReporter = requireNonNull(slf4jReporter);
        this.configuration = requireNonNull(configuration);
    }

    @Override
    protected void startUp() throws Exception {
        if (configuration.isEnabled()) {
            final Duration reportInterval = configuration.getReportInterval();
            slf4jReporter.start(reportInterval.getQuantity(), reportInterval.getUnit());
        }
    }

    @Override
    protected void shutDown() throws Exception {
        if (configuration.isEnabled()) {
            slf4jReporter.stop();
        }
    }
}
