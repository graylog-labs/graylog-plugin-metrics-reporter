/**
 * This file is part of Graylog Metrics Reporter Core Classes.
 *
 * Graylog Metrics Reporter Core Classes is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics Reporter Core Classes is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics Reporter Core Classes.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.core;

import org.graylog2.plugin.PluginMetaData;
import org.graylog2.plugin.ServerStatus;
import org.graylog2.plugin.Version;

import java.net.URI;
import java.util.Collections;
import java.util.Set;

public abstract class BasePluginMetaData implements PluginMetaData {
    protected abstract String getPluginProperties();

    @Override
    public String getUniqueId() {
        return this.getClass().getCanonicalName();
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
        return Version.fromPluginProperties(getClass(), getPluginProperties(), "version", Version.from(2, 4, 0));
    }

    @Override
    public Version getRequiredVersion() {
        return Version.fromPluginProperties(getClass(), getPluginProperties(), "graylog.version", Version.from(2, 4, 0));
    }
}
