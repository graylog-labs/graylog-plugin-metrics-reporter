/**
 * This file is part of Graylog Metrics Cassandra Reporter Plugin.
 *
 * Graylog Metrics Cassandra Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics Cassandra Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics Cassandra Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.cassandra;

import com.codahale.metrics.cassandra.Cassandra;
import com.codahale.metrics.cassandra.CassandraReporter;
import org.graylog.plugins.metrics.cassandra.providers.CassandraProvider;
import org.graylog.plugins.metrics.cassandra.providers.CassandraReporterProvider;
import org.graylog2.plugin.PluginConfigBean;
import org.graylog2.plugin.PluginModule;

import java.util.Collections;
import java.util.Set;

public class MetricsCassandraReporterModule extends PluginModule {
    @Override
    public Set<? extends PluginConfigBean> getConfigBeans() {
        return Collections.singleton(new MetricsCassandraReporterConfiguration());
    }

    @Override
    protected void configure() {
        bind(Cassandra.class).toProvider(CassandraProvider.class);
        bind(CassandraReporter.class).toProvider(CassandraReporterProvider.class);

        addConfigBeans();
        addInitializer(MetricsCassandraReporterService.class);
    }
}
