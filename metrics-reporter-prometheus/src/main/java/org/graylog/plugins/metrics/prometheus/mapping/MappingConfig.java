package org.graylog.plugins.metrics.prometheus.mapping;

public class MappingConfig {

    private final String pattern;
    private final String[] labels;

    public MappingConfig(final String pattern, final String[] labels) {
        this.pattern = pattern;
        this.labels = labels;
    }

    public String getPattern() {
        return pattern;
    }

    public String[] getLabels() {
        return labels;
    }
}
