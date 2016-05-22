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

import com.google.common.net.HostAndPort;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.PushGateway;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.graylog.plugins.metrics.prometheus.MetricsPrometheusReporterConfiguration;
import org.junit.Test;

import java.net.HttpURLConnection;

import static org.junit.Assert.assertEquals;

public class PushGatewayProviderTest {
    @Test
    public void getReturnsPushGatewayProvider() throws Exception {
        final MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_ACCEPTED));
        server.start();

        final MetricsPrometheusReporterConfiguration configuration = new MetricsPrometheusReporterConfiguration() {
            @Override
            public HostAndPort getAddress() {
                return HostAndPort.fromParts(server.getHostName(), server.getPort());
            }
        };
        final PushGatewayProvider provider = new PushGatewayProvider(configuration);
        final PushGateway pushGateway = provider.get();
        pushGateway.push(CollectorRegistry.defaultRegistry, "test");

        final RecordedRequest request = server.takeRequest();
        assertEquals("POST", request.getMethod());
        assertEquals("/metrics/job/test", request.getPath());
        assertEquals("text/plain; version=0.0.4; charset=utf-8", request.getHeader("Content-Type"));
        assertEquals(0L, request.getBodySize());
    }
}