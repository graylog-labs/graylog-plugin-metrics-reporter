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
package org.graylog.plugins.metrics.prometheus.rest;

import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;
import com.google.common.collect.ImmutableList;
import com.google.inject.Module;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.graylog.plugins.metrics.prometheus.MetricsPrometheusReporterModule;
import org.graylog2.shared.bindings.GuiceInjectorHolder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MetricsResourceTest extends JerseyTest {
    public MetricsResourceTest() {
        final Module metricsModule = binder -> {
            final MetricRegistry registry = new MetricRegistry();
            final Counter counter = registry.counter("test.counter");
            counter.inc(42L);
            registry.counter(
                    "org.graylog2.inputs.gelf.http.GELFHttpInput.5836b5852b3f8c1fad705b3b.rawSize.total");

            binder.bind(MetricRegistry.class).toInstance(registry);
        };
        GuiceInjectorHolder.createInjector(ImmutableList.of(new MetricsPrometheusReporterModule(), metricsModule));
    }

    @Override
    protected Application configure() {
        return new ResourceConfig(MetricsResource.class);
    }

    @Test
    public void prometheusMetricStreamsMetrics() throws Exception {
        final Response response = target("/metrics").request().get();
        assertEquals(200, response.getStatus());
        assertEquals("text/plain;charset=utf-8;version=0.0.4",
                response.getHeaderString("Content-Type"));

        final String body = response.readEntity(String.class);
        assertTrue(body.contains("test_counter 42.0\n"));
        assertTrue(body.contains(
                "org_graylog2_inputs_gelf_http_GELFHttpInput_rawSize_total{hash=\"5836b5852b3f8c1fad705b3b\",} 0.0\n"));
    }
}