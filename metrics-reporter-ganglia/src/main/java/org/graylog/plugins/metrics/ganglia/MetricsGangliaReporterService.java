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
package org.graylog.plugins.metrics.ganglia;

import com.codahale.metrics.ganglia.GangliaReporter;
import com.github.joschi.jadconfig.util.Duration;
import com.google.common.util.concurrent.AbstractIdleService;

import javax.inject.Inject;

import static java.util.Objects.requireNonNull;

public class MetricsGangliaReporterService extends AbstractIdleService {
    private final GangliaReporter gangliaReporter;
    private final MetricsGangliaReporterConfiguration configuration;

    @Inject
    public MetricsGangliaReporterService(GangliaReporter gangliaReporter,
                                         MetricsGangliaReporterConfiguration configuration) {
        this.gangliaReporter = requireNonNull(gangliaReporter);
        this.configuration = requireNonNull(configuration);
    }

    @Override
    protected void startUp() throws Exception {
        if (configuration.isEnabled()) {
            final Duration interval = configuration.getReportInterval();
            gangliaReporter.start(interval.getQuantity(), interval.getUnit());
        }
    }

    @Override
    protected void shutDown() throws Exception {
        if (configuration.isEnabled()) {
            gangliaReporter.stop();
        }
    }
}
