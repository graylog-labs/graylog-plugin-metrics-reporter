/**
 * This file is part of Graylog Metrics JMX Reporter Plugin.
 *
 * Graylog Metrics JMX Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics JMX Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics JMX Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.jmx;

import com.codahale.metrics.JmxReporter;
import org.graylog.plugins.metrics.jmx.providers.JmxReporterProvider;
import org.graylog2.plugin.PluginConfigBean;
import org.graylog2.plugin.PluginModule;

import java.util.Collections;
import java.util.Set;

public class MetricsJmxReporterModule extends PluginModule {
    @Override
    public Set<? extends PluginConfigBean> getConfigBeans() {
        return Collections.singleton(new MetricsJmxReporterConfiguration());
    }

    @Override
    protected void configure() {
        bind(JmxReporter.class).toProvider(JmxReporterProvider.class);

        addConfigBeans();
        addInitializer(MetricsJmxReporterService.class);
    }
}
