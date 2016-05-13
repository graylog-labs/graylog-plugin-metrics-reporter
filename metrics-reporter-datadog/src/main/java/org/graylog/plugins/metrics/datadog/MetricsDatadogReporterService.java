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
package org.graylog.plugins.metrics.datadog;

import com.github.joschi.jadconfig.util.Duration;
import com.google.common.util.concurrent.AbstractIdleService;
import org.coursera.metrics.datadog.DatadogReporter;

import javax.inject.Inject;

import static java.util.Objects.requireNonNull;

public class MetricsDatadogReporterService extends AbstractIdleService {
    private final DatadogReporter datadogReporter;
    private final MetricsDatadogReporterConfiguration configuration;

    @Inject
    public MetricsDatadogReporterService(DatadogReporter datadogReporter,
                                         MetricsDatadogReporterConfiguration configuration) {
        this.datadogReporter = requireNonNull(datadogReporter);
        this.configuration = requireNonNull(configuration);
    }

    @Override
    protected void startUp() throws Exception {
        if (configuration.isEnabled()) {
            final Duration interval = configuration.getReportInterval();
            datadogReporter.start(interval.getQuantity(), interval.getUnit());
        }
    }

    @Override
    protected void shutDown() throws Exception {
        if (configuration.isEnabled()) {
            datadogReporter.stop();
        }
    }
}
