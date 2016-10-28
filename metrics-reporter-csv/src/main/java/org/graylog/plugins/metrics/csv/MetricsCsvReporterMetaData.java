/**
 * This file is part of Graylog Metrics CSV Reporter Plugin.
 *
 * Graylog Metrics CSV Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics CSV Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics CSV Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.csv;

import org.graylog.plugins.metrics.core.BasePluginMetaData;

public class MetricsCsvReporterMetaData extends BasePluginMetaData {
    @Override
    public String getName() {
        return "Internal Metrics CSV Reporter";
    }

    @Override
    public String getDescription() {
        return "A plugin for reporting internal Graylog metrics into CSV files.";
    }
}
