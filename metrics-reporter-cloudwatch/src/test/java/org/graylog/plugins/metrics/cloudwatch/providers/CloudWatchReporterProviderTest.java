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

import static org.junit.Assert.assertNotNull;

import org.graylog.plugins.metrics.cloudwatch.MetricsCloudWatchReporterConfiguration;
import org.junit.Test;

import com.blacklocus.metrics.CloudWatchReporter;
import com.codahale.metrics.MetricRegistry;

public class CloudWatchReporterProviderTest {
    @Test
    public void get() throws Exception {
        final MetricsCloudWatchReporterConfiguration configuration = new MetricsCloudWatchReporterConfiguration();
        final CloudWatchReporterProvider provider = new CloudWatchReporterProvider(configuration, new MetricRegistry());
        final CloudWatchReporter reporter = provider.get();
        assertNotNull(reporter);
    }
}