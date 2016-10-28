/**
 * This file is part of Graylog Metrics GELF Reporter Plugin.
 *
 * Graylog Metrics GELF Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics GELF Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics GELF Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.gelf;

import org.graylog2.plugin.PluginMetaData;
import org.graylog2.plugin.ServerStatus;
import org.graylog2.plugin.Version;

import java.net.URI;
import java.util.Collections;
import java.util.Set;

public class MetricsGelfReporterMetaData implements PluginMetaData {

    @Override
    public String getUniqueId() {
        return MetricsGelfReporterPlugin.class.getCanonicalName();
    }

    @Override
    public String getName() {
        return "Internal Metrics GELF Reporter";
    }


    @Override
    public Set<ServerStatus.Capability> getRequiredCapabilities() {
        return Collections.emptySet();
    }

    @Override
    public String getAuthor() {
        return "Graylog, Inc.";
    }

    @Override
    public URI getURL() {
        return URI.create("https://www.graylog.org/");
    }

    @Override
    public Version getVersion() {
        return new Version(1, 2, 0);
    }

    @Override
    public String getDescription() {
        return "A plugin for reporting internal Graylog metrics via GELF.";
    }

    @Override
    public Version getRequiredVersion() {
        return new Version(2, 0, 0);
    }
}
