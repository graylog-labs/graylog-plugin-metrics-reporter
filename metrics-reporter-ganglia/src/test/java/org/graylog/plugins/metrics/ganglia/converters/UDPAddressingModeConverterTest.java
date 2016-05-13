/**
 * This file is part of Graylog Metrics Ganglia Reporter Plugin.
 *
 * Graylog Metrics Ganglia Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics Ganglia Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics Ganglia Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.ganglia.converters;

import com.github.joschi.jadconfig.ParameterException;
import info.ganglia.gmetric4j.gmetric.GMetric;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class UDPAddressingModeConverterTest {
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void convertFromValidString() throws Exception {
        Assert.assertEquals(GMetric.UDPAddressingMode.MULTICAST, new UDPAddressingModeConverter().convertFrom("MULTICAST"));
    }

    @Test
    public void convertFromValidLowerCaseString() throws Exception {
        assertEquals(GMetric.UDPAddressingMode.UNICAST, new UDPAddressingModeConverter().convertFrom("unicast"));
    }

    @Test
    public void convertFromInvalidString() throws Exception {
        expectedException.expect(ParameterException.class);
        expectedException.expectMessage("Couldn't convert value \"Foobar\" to UDP addressing mode.");
        new UDPAddressingModeConverter().convertFrom("Foobar");
    }

    @Test
    public void convertFromNull() throws Exception {
        expectedException.expect(ParameterException.class);
        expectedException.expectMessage("Couldn't convert value \"null\" to UDP addressing mode.");
        new UDPAddressingModeConverter().convertFrom(null);
    }

    @Test
    public void convertTo() throws Exception {
        assertEquals("MULTICAST", new UDPAddressingModeConverter().convertTo(GMetric.UDPAddressingMode.MULTICAST));
    }

    @Test
    public void convertToWithNull() throws Exception {
        expectedException.expect(ParameterException.class);
        expectedException.expectMessage("Couldn't convert \"null\" to string.");
        new UDPAddressingModeConverter().convertTo(null);
    }
}