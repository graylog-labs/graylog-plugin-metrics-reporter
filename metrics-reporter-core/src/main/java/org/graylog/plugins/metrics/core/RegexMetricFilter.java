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

import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricFilter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMetricFilter implements MetricFilter {
    private final Collection<Pattern> patterns;

    public RegexMetricFilter(Collection<Pattern> patterns) {
        this.patterns = new ArrayList<>(patterns);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean matches(String name, Metric metric) {
        for (Pattern pattern : patterns) {
            final Matcher matcher = pattern.matcher(name);
            if (matcher.find()) {
                return true;
            }
        }
        return false;
    }
}
