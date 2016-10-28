/**
 * This file is part of Graylog Metrics GELF Reporter Plugin.
 *
 * Graylog Metrics GELF Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics GELF Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics GELF Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.gelf;

import com.github.joschi.jadconfig.util.Duration;
import com.google.common.util.concurrent.AbstractIdleService;
import org.graylog.metrics.GelfReporter;

import javax.inject.Inject;

import static java.util.Objects.requireNonNull;

public class MetricsGelfReporterService extends AbstractIdleService {
    private final MetricsGelfReporterConfiguration configuration;
    private final GelfReporter gelfReporter;

    @Inject
    public MetricsGelfReporterService(GelfReporter gelfReporter, MetricsGelfReporterConfiguration configuration) {
        this.gelfReporter = requireNonNull(gelfReporter);
        this.configuration = requireNonNull(configuration);
    }

    @Override
    protected void startUp() throws Exception {
        if (configuration.isEnabled()) {
            final Duration reportInterval = configuration.getReportInterval();
            gelfReporter.start(reportInterval.getQuantity(), reportInterval.getUnit());
        }
    }

    @Override
    protected void shutDown() throws Exception {
        if (configuration.isEnabled()) {
            gelfReporter.stop();
        }
    }
}
