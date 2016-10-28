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
package org.graylog.plugins.metrics.gelf.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;
import com.google.common.base.Strings;
import org.graylog2.gelfclient.GelfTransports;

import java.util.Locale;

public class GelfTransportsConverter implements Converter<GelfTransports> {
    @Override
    public GelfTransports convertFrom(String value) {
        try {
            return GelfTransports.valueOf(Strings.nullToEmpty(value).toUpperCase(Locale.ENGLISH));
        } catch (IllegalArgumentException e) {
            throw new ParameterException("Couldn't convert value \"" + value + "\" to GELF transport.", e);
        }
    }

    @Override
    public String convertTo(GelfTransports value) {
        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to string.");
        }
        return value.name();
    }
}
