/**
 * This file is part of Graylog Metrics Cassandra Reporter Plugin.
 *
 * Graylog Metrics Cassandra Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics Cassandra Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics Cassandra Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.cassandra.jadconfig;

import com.datastax.driver.core.ConsistencyLevel;
import com.github.joschi.jadconfig.ParameterException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class ConsistencyLevelConverterTest {
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void convertFromValidString() throws Exception {
        final ConsistencyLevel consistencyLevel = new ConsistencyLevelConverter().convertFrom("QUORUM");
        assertEquals(ConsistencyLevel.QUORUM, consistencyLevel);
    }

    @Test
    public void convertFromValidMixedCaseString() throws Exception {
        final ConsistencyLevel consistencyLevel = new ConsistencyLevelConverter().convertFrom("local_One");
        assertEquals(ConsistencyLevel.LOCAL_ONE, consistencyLevel);
    }

    @Test
    public void convertFromInvalidString() throws Exception {
        expectedException.expect(ParameterException.class);
        expectedException.expectMessage("No enum constant com.datastax.driver.core.ConsistencyLevel.FOOBAR");
        new ConsistencyLevelConverter().convertFrom("foobar");
    }

    @Test
    public void convertFromNull() throws Exception {
        expectedException.expect(ParameterException.class);
        expectedException.expectMessage("Couldn't convert empty string to a Cassandra consistency level.");
        new ConsistencyLevelConverter().convertFrom(null);
    }

    @Test
    public void convertFromEmptyString() throws Exception {
        expectedException.expect(ParameterException.class);
        expectedException.expectMessage("Couldn't convert empty string to a Cassandra consistency level.");
        new ConsistencyLevelConverter().convertFrom("");
    }

    @Test
    public void convertToWithNull() throws Exception {
        expectedException.expect(ParameterException.class);
        expectedException.expectMessage("Couldn't convert \"null\" to string.");
        new ConsistencyLevelConverter().convertTo(null);
    }

    @Test
    public void convertToWithValidConsistencyLevel() throws Exception {
        final String s = new ConsistencyLevelConverter().convertTo(ConsistencyLevel.ALL);
        assertEquals("ALL", s);
    }
}