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
/**
 * This file is part of Graylog Metrics Elasticsearch Reporter Plugin.
 *
 * Graylog Metrics Elasticsearch Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics Elasticsearch Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics Elasticsearch Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.core.jadconfig;

import com.github.joschi.jadconfig.ParameterException;
import com.google.common.collect.ImmutableList;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PatternListConverterTest {
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void convertFromValidString() throws Exception {
        final List<Pattern> patterns = new PatternListConverter().convertFrom("foobar.*,\n foo(bar|baz)");
        assertEquals(2, patterns.size());
        assertEquals("foobar.*", patterns.get(0).pattern());
        assertEquals("foo(bar|baz)", patterns.get(1).pattern());
    }

    @Test
    public void convertWithInvalidPattern() throws Exception {
        expectedException.expect(ParameterException.class);
        expectedException.expectMessage("Dangling meta character '*' near index 0");
        new PatternListConverter().convertFrom("*");
    }

    @Test
    public void convertFromNull() throws Exception {
        expectedException.expect(ParameterException.class);
        expectedException.expectMessage("Couldn't convert value \"null\" to a list of regular expressions.");
        new PatternListConverter().convertFrom(null);
    }

    @Test
    public void convertFromEmptyString() throws Exception {
        final List<Pattern> patterns = new PatternListConverter().convertFrom("");
        assertTrue(patterns.isEmpty());
    }

    @Test
    public void convertToWithNull() throws Exception {
        expectedException.expect(ParameterException.class);
        expectedException.expectMessage("Couldn't convert \"null\" to string.");
        new PatternListConverter().convertTo(null);
    }

    @Test
    public void convertFromWithBlankValues() throws Exception {
        final List<Pattern> patterns = new PatternListConverter().convertFrom("foobar.*,,,foo(bar|baz)");
        assertEquals(2, patterns.size());
        assertEquals("foobar.*", patterns.get(0).pattern());
        assertEquals("foo(bar|baz)", patterns.get(1).pattern());
    }

    @Test
    public void convertToWithEmptyList() throws Exception {
        final String s = new PatternListConverter().convertTo(Collections.emptyList());
        assertEquals("", s);
    }

    @Test
    public void convertToWithRegularList() throws Exception {
        final String s = new PatternListConverter().convertTo(
                ImmutableList.of(Pattern.compile("foobar.*"), Pattern.compile("foo(bar|baz)")));
        assertEquals("foobar.*,foo(bar|baz)", s);
    }

    @Test
    public void convertToWithListContainingNull() throws Exception {
        final List<Pattern> patterns = new ArrayList<>();
        patterns.add(Pattern.compile("foobar.*"));
        patterns.add(null);
        patterns.add(Pattern.compile("foo(bar|baz)"));
        final String s = new PatternListConverter().convertTo(patterns);
        assertEquals("foobar.*,foo(bar|baz)", s);
    }
}