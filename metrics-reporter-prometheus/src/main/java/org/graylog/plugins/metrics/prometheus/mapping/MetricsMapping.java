package org.graylog.plugins.metrics.prometheus.mapping;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MetricsMapping {

    private final List<MappingConfig> configs;

    public MetricsMapping() {
        configs = new LinkedList<>();
        configs.add(new MappingConfig(
                "org.graylog2.inputs.gelf.http.GELFHttpInput.(.*).rawSize.total",
                new String[] { "hash" }));
    }

    public Map<String, String> matches(String metricName) {
        for (MappingConfig mc : configs) {
            Map<String, String> match = match(metricName, mc.getPattern(), mc.getLabels());
            int size = match.size();
            if (size > 0) {
                return match;
            }
        }
        return new HashMap<String, String>();
    }

    // package-private for testing
    Map<String, String> match(final String metricName, final String pattern, String[] labels)
            throws IllegalArgumentException {
        if (labels.length != (pattern.length() - pattern.replace("*", "").length())) {
            throw new IllegalArgumentException();
        }
        final HashMap<String, String> m = new HashMap<>();
        final Pattern p = Pattern.compile(pattern);
        final Matcher matcher = p.matcher(metricName);
        for (int i = 0; matcher.find(); i++) {
            final String group = matcher.group(i + 1);
            m.put(labels[i], group);
        }
        return m;
    }
}