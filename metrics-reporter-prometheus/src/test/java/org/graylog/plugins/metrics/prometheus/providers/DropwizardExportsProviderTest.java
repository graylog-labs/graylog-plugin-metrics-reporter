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
package org.graylog.plugins.metrics.prometheus.providers;

import com.codahale.metrics.MetricRegistry;
import io.prometheus.client.Collector;
import io.prometheus.client.dropwizard.DropwizardExports;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DropwizardExportsProviderTest {
    @Test
    public void getReturnsDropwizardExportsWithDropwizardMetrics() throws Exception {
        final MetricRegistry registry = new MetricRegistry();
        registry.histogram("test.histogram");
        final DropwizardExports dropwizardExports = new DropwizardExports(registry);
        final List<Collector.MetricFamilySamples> samples = dropwizardExports.collect();
        assertEquals(1, samples.size());

        final Collector.MetricFamilySamples element = samples.get(0);
        assertEquals("test_histogram", element.name);
        assertEquals(Collector.Type.SUMMARY, element.type);
    }
}