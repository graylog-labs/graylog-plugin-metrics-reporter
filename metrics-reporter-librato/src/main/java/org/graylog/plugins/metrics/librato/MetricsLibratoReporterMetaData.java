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

import org.graylog.plugins.metrics.core.BasePluginMetaData;

public class MetricsLibratoReporterMetaData extends BasePluginMetaData {
    @Override
    public String getName() {
        return "Internal Metrics Librato Reporter";
    }

    @Override
    public String getDescription() {
        return "A plugin for reporting internal Graylog metrics to Librato.";
    }
}
