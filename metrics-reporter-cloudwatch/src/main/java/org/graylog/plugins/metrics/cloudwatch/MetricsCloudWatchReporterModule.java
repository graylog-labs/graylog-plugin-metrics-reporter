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

import java.util.Collections;
import java.util.Set;

import org.graylog.plugins.metrics.cloudwatch.providers.CloudWatchReporterProvider;
import org.graylog2.plugin.PluginConfigBean;
import org.graylog2.plugin.PluginModule;

import com.blacklocus.metrics.CloudWatchReporter;

public class MetricsCloudWatchReporterModule extends PluginModule {
    @Override
    public Set<? extends PluginConfigBean> getConfigBeans() {
        return Collections.singleton(new MetricsCloudWatchReporterConfiguration());
    }

    @Override
    protected void configure() {
        bind(CloudWatchReporter.class).toProvider(CloudWatchReporterProvider.class);

        addConfigBeans();
        addInitializer(MetricsCloudWatchReporterService.class);
    }
}
