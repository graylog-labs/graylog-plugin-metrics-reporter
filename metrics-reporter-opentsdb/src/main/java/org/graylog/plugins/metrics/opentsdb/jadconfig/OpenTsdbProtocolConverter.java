/**
 * This file is part of Graylog Metrics OpenTSDB Reporter Plugin.
 *
 * Graylog Metrics OpenTSDB Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics OpenTSDB Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics OpenTSDB Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.opentsdb.jadconfig;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;
import com.google.common.base.Strings;
import org.graylog.plugins.metrics.opentsdb.OpenTsdbProtocol;

import java.util.Locale;

public class OpenTsdbProtocolConverter implements Converter<OpenTsdbProtocol> {
    @Override
    public OpenTsdbProtocol convertFrom(String value) {
        if (Strings.isNullOrEmpty(value)) {
            throw new ParameterException("Couldn't convert empty string to \"HTTP\" or \"TELNET\".");
        }

        try {
            return OpenTsdbProtocol.valueOf(value.toUpperCase(Locale.ENGLISH));
        } catch (Exception e) {
            throw new ParameterException(e.getMessage(), e);
        }
    }

    @Override
    public String convertTo(OpenTsdbProtocol value) {
        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to string.");
        }

        return value.name();
    }
}
