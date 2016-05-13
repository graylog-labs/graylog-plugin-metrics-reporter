/**
 * This file is part of Graylog Metrics statsd Reporter Plugin.
 *
 * Graylog Metrics statsd Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics statsd Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics statsd Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.statsd;

import com.basistech.metrics.reporting.Statsd;
import com.basistech.metrics.reporting.StatsdReporter;
import org.graylog.plugins.metrics.statsd.providers.StatsdProvider;
import org.graylog.plugins.metrics.statsd.providers.StatsdReporterProvider;
import org.graylog2.plugin.PluginConfigBean;
import org.graylog2.plugin.PluginModule;

import java.util.Collections;
import java.util.Set;

public class MetricsStatsdReporterModule extends PluginModule {
    @Override
    public Set<? extends PluginConfigBean> getConfigBeans() {
        return Collections.singleton(new MetricsStatsdReporterConfiguration());
    }

    @Override
    protected void configure() {
        bind(Statsd.class).toProvider(StatsdProvider.class);
        bind(StatsdReporter.class).toProvider(StatsdReporterProvider.class);

        addConfigBeans();
        addInitializer(MetricsStatsdReporterService.class);
    }
}
