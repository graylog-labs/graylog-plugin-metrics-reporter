/**
 * This file is part of Graylog Metrics Elasticsearch Reporter Plugin.
 *
 * Graylog Metrics Elasticsearch Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics Elasticsearch Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics Elasticsearch Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.cloudwatch;

import com.google.auto.service.AutoService;
import org.graylog2.plugin.Plugin;
import org.graylog2.plugin.PluginMetaData;
import org.graylog2.plugin.PluginModule;

import java.util.Collection;
import java.util.Collections;

@AutoService(Plugin.class)
public class MetricsCloudWatchReporterPlugin implements Plugin {
    @Override
    public PluginMetaData metadata() {
        return new MetricsCloudWatchReporterMetaData();
    }

    @Override
    public Collection<PluginModule> modules() {
        return Collections.singleton(new MetricsCloudWatchReporterModule());
    }
}
