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

import com.github.joschi.jadconfig.ParameterException;
import org.graylog.plugins.metrics.opentsdb.OpenTsdbProtocol;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class OpenTsdbProtocolConverterTest {
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void convertFromValidString() throws Exception {
        final OpenTsdbProtocol protocol = new OpenTsdbProtocolConverter().convertFrom("HTTP");
        assertEquals(OpenTsdbProtocol.HTTP, protocol);
    }

    @Test
    public void convertFromValidMixedCaseString() throws Exception {
        final OpenTsdbProtocol protocol = new OpenTsdbProtocolConverter().convertFrom("telNet");
        assertEquals(OpenTsdbProtocol.TELNET, protocol);
    }

    @Test
    public void convertFromInvalidString() throws Exception {
        expectedException.expect(ParameterException.class);
        expectedException.expectMessage("No enum constant org.graylog.plugins.metrics.opentsdb.OpenTsdbProtocol.FOOBAR");
        new OpenTsdbProtocolConverter().convertFrom("foobar");
    }

    @Test
    public void convertFromNull() throws Exception {
        expectedException.expect(ParameterException.class);
        expectedException.expectMessage("Couldn't convert empty string to \"HTTP\" or \"TELNET\".");
        new OpenTsdbProtocolConverter().convertFrom(null);
    }

    @Test
    public void convertFromEmptyString() throws Exception {
        expectedException.expect(ParameterException.class);
        expectedException.expectMessage("Couldn't convert empty string to \"HTTP\" or \"TELNET\".");
        new OpenTsdbProtocolConverter().convertFrom("");
    }

    @Test
    public void convertToWithNull() throws Exception {
        expectedException.expect(ParameterException.class);
        expectedException.expectMessage("Couldn't convert \"null\" to string.");
        new OpenTsdbProtocolConverter().convertTo(null);
    }

    @Test
    public void convertToWithValidOpenTsdbProtocol() throws Exception {
        final String s = new OpenTsdbProtocolConverter().convertTo(OpenTsdbProtocol.HTTP);
        assertEquals("HTTP", s);
    }
}