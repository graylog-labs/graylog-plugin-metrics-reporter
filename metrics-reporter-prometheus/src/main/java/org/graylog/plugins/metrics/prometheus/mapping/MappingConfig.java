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

/**
 * Provide a mapping between metric names and labels. It encapsulates both a
 * pattern (to extract subsequences of metric names) and label names for these
 * extracted parts. E.g.:
 *
 * metric name: "plugin.1234.output.counter" has a plugin id as part of the
 * metric name
 *
 * Constructing a new {@link MappingConfig} with:
 *
 * name: newName_counter pattern: "plugin.(.*).output.conter" matches this name
 * and selects the value in a group lables: {"pluginId"}
 *
 * Encapsulates all the info necessary to extract the part of the name and store
 * it as a label of the given name. The resulting metric will be:
 *
 * newName_counter with a label pluginId=1234
 *
 * @author neumayer
 *
 */
public class MappingConfig {

    private final String name;
    private final String pattern;
    private final String[] labels;
    private static final String GROUP_IDENTIFIER = "(.*)";

    /**
     * Standard constructor.
     *
     * @param name
     *            of the resulting metric
     * @param pattern
     *            follows group syntax of java regular expressions (i.e.
     *            a.(.*).c would extract "b" from "a.b.c")
     * @param labels
     *            labels for the groups in the pattern
     * @throws MappingConfigSyntaxException
     *             if number of groups and length of labels don't match
     */
    public MappingConfig(final String name, final String pattern, final String[] labels)
            throws MappingConfigSyntaxException {
        if (labels.length * GROUP_IDENTIFIER.length()
                != (pattern.length() - pattern.replace(GROUP_IDENTIFIER, "").length())) {
            throw new MappingConfigSyntaxException();
        }
        this.name = name;
        this.pattern = pattern;
        this.labels = labels;
    }

    public String getName() {
        return name;
    }

    public String getPattern() {
        return pattern;
    }

    public String[] getLabels() {
        return labels;
    }
}
