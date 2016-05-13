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

import com.codahale.metrics.Slf4jReporter;
import com.github.joschi.jadconfig.ParameterException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class LoggingLevelConverterTest {
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void convertFromValidString() throws Exception {
        assertEquals(Slf4jReporter.LoggingLevel.INFO, new LoggingLevelConverter().convertFrom("INFO"));
    }

    @Test
    public void convertFromValidLowerCaseString() throws Exception {
        assertEquals(Slf4jReporter.LoggingLevel.WARN, new LoggingLevelConverter().convertFrom("warn"));
    }

    @Test
    public void convertFromInvalidString() throws Exception {
        expectedException.expect(ParameterException.class);
        expectedException.expectMessage("Couldn't convert value \"Foobar\" to log level.");
        new LoggingLevelConverter().convertFrom("Foobar");
    }

    @Test
    public void convertFromNull() throws Exception {
        expectedException.expect(ParameterException.class);
        expectedException.expectMessage("Couldn't convert value \"null\" to log level.");
        new LoggingLevelConverter().convertFrom(null);
    }

    @Test
    public void convertTo() throws Exception {
        assertEquals("ERROR", new LoggingLevelConverter().convertTo(Slf4jReporter.LoggingLevel.ERROR));
    }

    @Test
    public void convertToWithNull() throws Exception {
        expectedException.expect(ParameterException.class);
        expectedException.expectMessage("Couldn't convert \"null\" to string.");
        new LoggingLevelConverter().convertTo(null);
    }
}