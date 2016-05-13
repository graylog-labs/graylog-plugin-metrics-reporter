/**
 * This file is part of Graylog Metrics Graphite Reporter Plugin.
 *
 * Graylog Metrics Graphite Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics Graphite Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics Graphite Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.graphite.converters;

import com.github.joschi.jadconfig.ParameterException;
import org.graylog.plugins.metrics.graphite.GraphiteProtocol;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class GraphiteProtocolConverterTest {
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void convertFromValidString() throws Exception {
        Assert.assertEquals(GraphiteProtocol.TCP, new GraphiteProtocolConverter().convertFrom("TCP"));
    }

    @Test
    public void convertFromValidLowerCaseString() throws Exception {
        assertEquals(GraphiteProtocol.TCP, new GraphiteProtocolConverter().convertFrom("tcp"));
    }

    @Test
    public void convertFromInvalidString() throws Exception {
        expectedException.expect(ParameterException.class);
        expectedException.expectMessage("Couldn't convert value \"Foobar\" to Graphite protocol.");
        new GraphiteProtocolConverter().convertFrom("Foobar");
    }

    @Test
    public void convertFromNull() throws Exception {
        expectedException.expect(ParameterException.class);
        expectedException.expectMessage("Couldn't convert value \"null\" to Graphite protocol.");
        new GraphiteProtocolConverter().convertFrom(null);
    }

    @Test
    public void convertTo() throws Exception {
        assertEquals("TCP", new GraphiteProtocolConverter().convertTo(GraphiteProtocol.TCP));
    }

    @Test
    public void convertToWithNull() throws Exception {
        expectedException.expect(ParameterException.class);
        expectedException.expectMessage("Couldn't convert \"null\" to string.");
        new GraphiteProtocolConverter().convertTo(null);
    }
}