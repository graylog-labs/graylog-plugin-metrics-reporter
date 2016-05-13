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

import org.coursera.metrics.datadog.DatadogReporter;
import org.coursera.metrics.datadog.transport.Transport;
import org.graylog.plugins.metrics.datadog.providers.DatadogReporterProvider;
import org.graylog.plugins.metrics.datadog.providers.TransportProvider;
import org.graylog2.plugin.PluginConfigBean;
import org.graylog2.plugin.PluginModule;

import java.util.Collections;
import java.util.Set;

public class MetricsDatadogReporterModule extends PluginModule {
    @Override
    public Set<? extends PluginConfigBean> getConfigBeans() {
        return Collections.singleton(new MetricsDatadogReporterConfiguration());
    }

    @Override
    protected void configure() {
        bind(Transport.class).toProvider(TransportProvider.class);
        bind(DatadogReporter.class).toProvider(DatadogReporterProvider.class);

        addConfigBeans();
        addInitializer(MetricsDatadogReporterService.class);
    }
}
