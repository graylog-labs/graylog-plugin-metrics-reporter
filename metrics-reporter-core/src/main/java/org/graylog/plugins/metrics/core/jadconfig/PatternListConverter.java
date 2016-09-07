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
package org.graylog.plugins.metrics.core.jadconfig;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PatternListConverter implements Converter<List<Pattern>> {
    private static final String SEPARATOR = ",";

    @Override
    public List<Pattern> convertFrom(String value) {
        if (value == null) {
            throw new ParameterException("Couldn't convert value \"null\" to a list of regular expressions.");
        }

        try {
            final Iterable<String> regexList = Splitter.on(SEPARATOR)
                    .omitEmptyStrings()
                    .trimResults()
                    .split(value);
            return StreamSupport.stream(regexList.spliterator(), false)
                    .map(Pattern::compile)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ParameterException(e.getMessage(), e);
        }
    }

    @Override
    public String convertTo(List<Pattern> value) {
        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to string.");
        }

        return Joiner.on(SEPARATOR)
                .skipNulls()
                .join(value);
    }
}
