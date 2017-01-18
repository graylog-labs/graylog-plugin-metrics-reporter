package org.graylog.plugins.metrics.prometheus.mapping;

import java.util.Map;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MetricsMappingTest {

    @Test
    public void testRegexp() {
        MetricsMapping mm = new MetricsMapping();
        final String metricName = "org.graylog2.inputs.gelf.http.GELFHttpInput.5836b5852b3f8c1fad705b3b.rawSize.total";
        final String pattern = "org.graylog2.inputs.gelf.http.GELFHttpInput.(.*).rawSize.total";
        final Map<String, String> labels = mm.match(metricName, pattern,
                new String[] { "hash" });
        assertEquals(labels.get("hash"), "5836b5852b3f8c1fad705b3b");
    }
}
