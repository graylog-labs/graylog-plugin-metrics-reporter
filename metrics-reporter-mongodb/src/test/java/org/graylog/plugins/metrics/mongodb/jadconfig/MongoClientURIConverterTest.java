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

import com.github.joschi.jadconfig.ParameterException;
import com.mongodb.MongoClientURI;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class MongoClientURIConverterTest {
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void convertFromValidConnectionString() throws Exception {
        final MongoClientURI uri = new MongoClientURIConverter().convertFrom("mongodb://127.0.0.1/test");
        assertEquals(new MongoClientURI("mongodb://127.0.0.1/test"), uri);
    }

    @Test
    public void convertFromInvalidConnectionString() throws Exception {
        expectedException.expect(ParameterException.class);
        expectedException.expectMessage("The connection string is invalid. Connection strings must start with 'mongodb://'");
        new MongoClientURIConverter().convertFrom("http://www.example.org/");
    }

    @Test
    public void convertFromEmptyString() throws Exception {
        expectedException.expect(ParameterException.class);
        expectedException.expectMessage("The connection string is invalid. Connection strings must start with 'mongodb://'");
        new MongoClientURIConverter().convertFrom("");
    }

    @Test
    public void convertFromNull() throws Exception {
        expectedException.expect(ParameterException.class);
        expectedException.expectMessage("Couldn't convert value \"null\" to a MongoDB connection string.");
        new MongoClientURIConverter().convertFrom(null);
    }

    @Test
    public void convertToWithNull() throws Exception {
        expectedException.expect(ParameterException.class);
        expectedException.expectMessage("Couldn't convert \"null\" to string.");
        new MongoClientURIConverter().convertTo(null);
    }

    @Test
    public void convertToWithValidMongoClientURI() throws Exception {
        final MongoClientURI uri = new MongoClientURI("mongodb://127.0.0.1/test");
        final String s = new MongoClientURIConverter().convertTo(uri);
        assertEquals("mongodb://127.0.0.1/test", s);
    }
}