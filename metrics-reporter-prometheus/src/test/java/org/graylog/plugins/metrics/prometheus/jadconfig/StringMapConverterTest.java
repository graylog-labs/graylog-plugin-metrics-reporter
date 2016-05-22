/**
 * This file is part of Graylog Metrics Prometheus Reporter Plugin.
 *
 * Graylog Metrics Prometheus Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics Prometheus Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics Prometheus Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.prometheus.jadconfig;

import com.github.joschi.jadconfig.ParameterException;
import com.google.common.collect.ImmutableMap;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StringMapConverterTest {
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void convertFromValidString() throws Exception {
        final Map<String, String> map = new StringMapConverter().convertFrom("key1:value1, key2:value2");
        assertEquals(2, map.size());
        assertEquals("value1", map.get("key1"));
        assertEquals("value2", map.get("key2"));
    }

    @Test
    public void convertFromInvalidString() throws Exception {
        expectedException.expect(ParameterException.class);
        expectedException.expectMessage("Chunk [key1;value1] is not a valid entry");
        new StringMapConverter().convertFrom("key1;value1, key2:value2");
    }

    @Test
    public void convertFromNull() throws Exception {
        expectedException.expect(ParameterException.class);
        expectedException.expectMessage("Couldn't convert value \"null\" to a map of strings.");
        new StringMapConverter().convertFrom(null);
    }

    @Test
    public void convertFromEmptyString() throws Exception {
        final Map<String, String> map = new StringMapConverter().convertFrom("");
        assertTrue(map.isEmpty());
    }

    @Test
    public void convertToWithNull() throws Exception {
        expectedException.expect(ParameterException.class);
        expectedException.expectMessage("Couldn't convert \"null\" to string.");
        new StringMapConverter().convertTo(null);
    }

    @Test
    public void convertToWithBlankKeysAndValues() throws Exception {
        final Map<String, String> map = new StringMapConverter().convertFrom("key1:, :value2");
        assertEquals(2, map.size());
        assertEquals("", map.get("key1"));
        assertEquals("value2", map.get(""));
    }

    @Test
    public void convertToWithEmptyMap() throws Exception {
        final String s = new StringMapConverter().convertTo(Collections.emptyMap());
        assertEquals("", s);
    }

    @Test
    public void convertToWithRegularMap() throws Exception {
        final String s = new StringMapConverter().convertTo(ImmutableMap.of("key1", "value1", "key2", "value2"));
        assertEquals("key1:value1,key2:value2", s);
    }

    @Test
    public void convertToWithMapContainingNull() throws Exception {
        final Map<String, String> map = new HashMap<>();
        map.put("key1", null);
        map.put(null, "value2");
        final String s = new StringMapConverter().convertTo(map);
        assertEquals("key1:,:value2", s);
    }

}