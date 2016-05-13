/**
 * This file is part of Graylog Metrics SLF4J Reporter Plugin.
 *
 * Graylog Metrics SLF4J Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics SLF4J Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics SLF4J Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.slf4j.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class MarkerConverter implements Converter<Marker> {
    @Override
    public Marker convertFrom(String value) {
        if (value == null) {
            throw new ParameterException("Couldn't convert value \"null\" to marker.");
        }
        return MarkerFactory.getMarker(value);
    }

    @Override
    public String convertTo(Marker marker) {
        if (marker == null) {
            throw new ParameterException("Couldn't convert \"null\" to string.");
        }
        return marker.getName();
    }
}
