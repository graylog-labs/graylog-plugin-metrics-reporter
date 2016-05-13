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
package org.graylog.plugins.metrics.influxdb;

import com.github.joschi.jadconfig.util.Duration;
import com.google.common.util.concurrent.AbstractIdleService;
import com.izettle.metrics.influxdb.InfluxDbReporter;

import javax.inject.Inject;

import static java.util.Objects.requireNonNull;

public class MetricsInfluxDbReporterService extends AbstractIdleService {
    private final MetricsInfluxDbReporterConfiguration configuration;
    private final InfluxDbReporter influxDbReporter;

    @Inject
    public MetricsInfluxDbReporterService(InfluxDbReporter influxDbReporter, MetricsInfluxDbReporterConfiguration configuration) {
        this.influxDbReporter = requireNonNull(influxDbReporter);
        this.configuration = requireNonNull(configuration);
    }

    @Override
    protected void startUp() throws Exception {
        if (configuration.isEnabled()) {
            final Duration reportInterval = configuration.getReportInterval();
            influxDbReporter.start(reportInterval.getQuantity(), reportInterval.getUnit());
        }
    }

    @Override
    protected void shutDown() throws Exception {
        if (configuration.isEnabled()) {
            influxDbReporter.stop();
        }
    }
}
