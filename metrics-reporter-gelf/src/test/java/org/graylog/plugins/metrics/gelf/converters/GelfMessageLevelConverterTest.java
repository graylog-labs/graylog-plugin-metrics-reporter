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

import com.github.joschi.jadconfig.ParameterException;
import org.graylog2.gelfclient.GelfMessageLevel;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class GelfMessageLevelConverterTest {
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void convertFromValidString() throws Exception {
        Assert.assertEquals(GelfMessageLevel.INFO, new GelfMessageLevelConverter().convertFrom("INFO"));
    }

    @Test
    public void convertFromValidLowerCaseString() throws Exception {
        assertEquals(GelfMessageLevel.ERROR, new GelfMessageLevelConverter().convertFrom("error"));
    }

    @Test
    public void convertFromInvalidString() throws Exception {
        expectedException.expect(ParameterException.class);
        expectedException.expectMessage("Couldn't convert value \"Foobar\" to GELF message level.");
        new GelfMessageLevelConverter().convertFrom("Foobar");
    }

    @Test
    public void convertFromNull() throws Exception {
        expectedException.expect(ParameterException.class);
        expectedException.expectMessage("Couldn't convert value \"null\" to GELF message level.");
        new GelfMessageLevelConverter().convertFrom(null);
    }

    @Test
    public void convertTo() throws Exception {
        assertEquals("WARNING", new GelfMessageLevelConverter().convertTo(GelfMessageLevel.WARNING));
    }

    @Test
    public void convertToWithNull() throws Exception {
        expectedException.expect(ParameterException.class);
        expectedException.expectMessage("Couldn't convert \"null\" to string.");
        new GelfMessageLevelConverter().convertTo(null);
    }
}