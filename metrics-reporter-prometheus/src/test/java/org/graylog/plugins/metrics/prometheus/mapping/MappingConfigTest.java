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
package org.graylog.plugins.metrics.prometheus.mapping;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MappingConfigTest {

    final String name = "testName";

    @Test
    public void shouldInitialise() throws MappingConfigSyntaxException {
        final String pattern = "a.(.*).c";
        final String[] labels = { "middleElement" };
        MappingConfig mappingConfig = new MappingConfig(name, pattern, labels);
        assertEquals(mappingConfig.getPattern(), pattern);
        assertEquals(mappingConfig.getLabels()[0], labels[0]);
    }

    @Test
    public void shouldThrowExceptionForInvalidSyntax() {
        final String pattern = "a.(.*).c.(.*)";
        final String[] labels = { "middleElement" };
        try {
            new MappingConfig(name, pattern, labels);
            fail();
        } catch (final MappingConfigSyntaxException e) {
        }
    }
}
