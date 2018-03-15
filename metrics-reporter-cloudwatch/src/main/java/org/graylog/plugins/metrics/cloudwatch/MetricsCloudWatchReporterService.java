/**
 * This file is part of Graylog Metrics CloudWatch Reporter Plugin.
 *
 * Graylog Metrics CloudWatch Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics CloudWatch Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics CloudWatch Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.cloudwatch;

import static java.util.Objects.requireNonNull;

import javax.inject.Inject;

import com.blacklocus.metrics.CloudWatchReporter;
import com.github.joschi.jadconfig.util.Duration;
import com.google.common.util.concurrent.AbstractIdleService;

public class MetricsCloudWatchReporterService extends AbstractIdleService {
    private final MetricsCloudWatchReporterConfiguration configuration;
    private final CloudWatchReporter cloudWatchReporter;

    @Inject
    public MetricsCloudWatchReporterService(CloudWatchReporter cloudWatchReporter, MetricsCloudWatchReporterConfiguration configuration) {
        this.cloudWatchReporter = requireNonNull(cloudWatchReporter);
        this.configuration = requireNonNull(configuration);
    }

    @Override
    protected void startUp() throws Exception {
        if (configuration.isEnabled()) {
            final Duration reportInterval = configuration.getReportInterval();
            cloudWatchReporter.start(reportInterval.getQuantity(), reportInterval.getUnit());
        }
    }

    @Override
    protected void shutDown() throws Exception {
        if (configuration.isEnabled()) {
        	cloudWatchReporter.stop();
        }
    }
}
