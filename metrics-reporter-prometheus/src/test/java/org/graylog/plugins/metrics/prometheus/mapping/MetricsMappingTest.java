package org.graylog.plugins.metrics.prometheus.mapping;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MetricsMappingTest {

    @Test
    public void shouldExtractLabelsForDefaultConfig() throws MappingConfigSyntaxException {
        final MetricMapping metricsMapping = new MetricMapping();
        final String metricName = "org.graylog2.inputs.gelf.http.GELFHttpInput.5836b5852b3f8c1fad705b3b.rawSize.total";
        final String pattern = "org.graylog2.inputs.gelf.http.GELFHttpInput.(.*).rawSize.total";
        final Map<String, String> labels = metricsMapping.extractLabelsValuesForMetric(metricName, pattern,
                new String[] { "hash" });
        assertEquals(labels.get("hash"), "5836b5852b3f8c1fad705b3b");
    }

    @Test
    public void shouldExtractAllLabels() throws MappingConfigSyntaxException {
        final List<MappingConfig> configs = new LinkedList<>();
        configs.add(new MappingConfig("test_metric", "a.(.*).c", new String[] { "middleElement" }));

        final MetricMapping metricsMapping = new MetricMapping(configs);
        final MetricLabels extractLabelValues = metricsMapping.extractAllLabelValues(
                "a.b.c");
        assertEquals(extractLabelValues.getLabelMap().get("middleElement"), "b");
        System.out.println(extractLabelValues);
    }

    @Test
    public void shouldNotExtractAnyLabels() throws MappingConfigSyntaxException {
        final List<MappingConfig> configs = new LinkedList<>();
        configs.add(new MappingConfig("test_metric", "a.(.*).c", new String[] { "middleElement" }));

        final MetricMapping metricsMapping = new MetricMapping(configs);
        final MetricLabels extractLabelValues = metricsMapping.extractAllLabelValues(
                "b.b.c");
        assertNull(extractLabelValues);
    }
}
