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
