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
package org.graylog.plugins.metrics.core;

import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.Set;
import java.util.SortedMap;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegexMetricFilterTest {
    private MetricRegistry metricRegistry;

    @Before
    public void setUp() {
        metricRegistry = new MetricRegistry();
    }

    @Test
    public void matchesReturnsTrueIfMetricNameIsMatched() throws Exception {
        final Set<Pattern> patterns = Collections.singleton(Pattern.compile("foobar"));
        final RegexMetricFilter regexMetricFilter = new RegexMetricFilter(patterns);

        final Counter counter1 = metricRegistry.counter("foobar.counter");
        metricRegistry.counter("foobaz.counter");

        final SortedMap<String, Counter> counters = metricRegistry.getCounters(regexMetricFilter);
        assertEquals(1, counters.size());
        assertEquals(counter1, counters.get("foobar.counter"));
    }

    @Test
    public void matchesReturnsFalseIfMetricNameCannotBeMatched() throws Exception {
        final Set<Pattern> patterns = Collections.singleton(Pattern.compile("quux"));
        final RegexMetricFilter regexMetricFilter = new RegexMetricFilter(patterns);

        metricRegistry.counter("foobar.counter");
        metricRegistry.counter("foobaz.counter");

        final SortedMap<String, Counter> counters = metricRegistry.getCounters(regexMetricFilter);
        assertTrue(counters.isEmpty());
    }

}