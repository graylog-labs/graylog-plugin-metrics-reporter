/**
 * This file is part of Graylog Metrics MongoDB Reporter Plugin.
 *
 * Graylog Metrics MongoDB Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics MongoDB Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics MongoDB Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.mongodb.jadconfig;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;
import com.mongodb.MongoClientURI;

public class MongoClientURIConverter implements Converter<MongoClientURI> {
    @Override
    public MongoClientURI convertFrom(String value) {
        if (value == null) {
            throw new ParameterException("Couldn't convert value \"null\" to a MongoDB connection string.");
        }

        try {
            return new MongoClientURI(value);
        } catch (Exception e) {
            throw new ParameterException(e.getMessage(), e);
        }
    }

    @Override
    public String convertTo(MongoClientURI value) {
        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to string.");
        }

        return value.toString();
    }
}
