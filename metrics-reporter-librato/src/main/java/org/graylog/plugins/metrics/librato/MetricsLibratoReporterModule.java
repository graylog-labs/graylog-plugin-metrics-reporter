/**
 * This file is part of Graylog Metrics Librato Reporter Plugin.
 *
 * Graylog Metrics Librato Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics Librato Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics Librato Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.librato;

import com.librato.metrics.reporter.LibratoReporter;
import org.graylog.plugins.metrics.librato.providers.LibratoReporterProvider;
import org.graylog2.plugin.PluginConfigBean;
import org.graylog2.plugin.PluginModule;

import java.util.Collections;
import java.util.Set;

public class MetricsLibratoReporterModule extends PluginModule {
    @Override
    public Set<? extends PluginConfigBean> getConfigBeans() {
        return Collections.singleton(new MetricsLibratoReporterConfiguration());
    }

    @Override
    protected void configure() {
        bind(LibratoReporter.class).toProvider(LibratoReporterProvider.class);

        addConfigBeans();
        addInitializer(MetricsLibratoReporterService.class);
    }
}
