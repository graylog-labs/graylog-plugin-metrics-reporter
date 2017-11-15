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
package org.graylog.plugins.metrics.cloudwatch.providers;

import static java.util.Objects.requireNonNull;

import javax.inject.Inject;
import javax.inject.Provider;

import org.graylog.plugins.metrics.cloudwatch.MetricsCloudWatchReporterConfiguration;
import org.graylog.plugins.metrics.core.RegexMetricFilter;

import com.amazonaws.services.cloudwatch.AmazonCloudWatchAsyncClientBuilder;
import com.blacklocus.metrics.CloudWatchReporter;
import com.blacklocus.metrics.CloudWatchReporterBuilder;
import com.codahale.metrics.MetricRegistry;

public class CloudWatchReporterProvider implements Provider<CloudWatchReporter> {
    private final MetricsCloudWatchReporterConfiguration configuration;
    private final MetricRegistry metricRegistry;

    @Inject
    public CloudWatchReporterProvider(MetricsCloudWatchReporterConfiguration configuration,
                                         MetricRegistry metricRegistry) {
        this.configuration = requireNonNull(configuration);
        this.metricRegistry = requireNonNull(metricRegistry);
    }

    @Override
    public CloudWatchReporter get() {
    	final AmazonCloudWatchAsyncClientBuilder amazonCloudWatchAsyncBuilder = AmazonCloudWatchAsyncClientBuilder.standard();
    	return new CloudWatchReporterBuilder()
        			.withClient(amazonCloudWatchAsyncBuilder.withRegion(configuration.getRegion()).build())
                    .withNamespace(configuration.getNamespace())
                    .withTimestampLocal(configuration.getTimestampLocal())
                    .withDimensions(configuration.getDimensions())
                    .withFilter(new RegexMetricFilter(configuration.getIncludeMetrics()))
                    .withRegistry(metricRegistry)
                    .build();
    }
}
