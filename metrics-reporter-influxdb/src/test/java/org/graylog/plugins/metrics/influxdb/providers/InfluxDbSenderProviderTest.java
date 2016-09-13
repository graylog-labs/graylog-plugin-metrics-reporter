/**
 * This file is part of Graylog Metrics InfluxDB Reporter Plugin.
 *
 * Graylog Metrics InfluxDB Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics InfluxDB Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics InfluxDB Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.influxdb.providers;

import com.izettle.metrics.influxdb.InfluxDbHttpSender;
import com.izettle.metrics.influxdb.InfluxDbSender;
import com.izettle.metrics.influxdb.InfluxDbTcpSender;
import com.izettle.metrics.influxdb.InfluxDbUdpSender;
import com.izettle.metrics.influxdb.data.InfluxDbWriteObject;
import org.graylog.plugins.metrics.influxdb.MetricsInfluxDbReporterConfiguration;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Field;
import java.net.URI;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InfluxDbSenderProviderTest {
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void getWithInvalidScheme() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Unsupported protocol \"invalid\"");

        final MetricsInfluxDbReporterConfiguration configuration = new MetricsInfluxDbReporterConfiguration() {
            @Override
            public URI getUri() {
                return URI.create("invalid://127.0.0.1:8086/database");
            }
        };
        final InfluxDbSenderProvider provider = new InfluxDbSenderProvider(configuration);
        provider.get();
    }

    @Test
    public void getCreatesHttpSender() throws Exception {
        final MetricsInfluxDbReporterConfiguration configuration = new MetricsInfluxDbReporterConfiguration() {
            @Override
            public URI getUri() {
                return URI.create("http://127.0.0.1:8086/database");
            }
        };
        final InfluxDbSenderProvider provider = new InfluxDbSenderProvider(configuration);
        final InfluxDbSender influxDbSender = provider.get();
        assertTrue(influxDbSender instanceof InfluxDbHttpSender);
    }

    @Test
    public void getCreatesTcpSender() throws Exception {
        final MetricsInfluxDbReporterConfiguration configuration = new MetricsInfluxDbReporterConfiguration() {
            @Override
            public URI getUri() {
                return URI.create("tcp://127.0.0.1:8086/database");
            }
        };
        final InfluxDbSenderProvider provider = new InfluxDbSenderProvider(configuration);
        final InfluxDbSender influxDbSender = provider.get();
        assertTrue(influxDbSender instanceof InfluxDbTcpSender);
    }

    @Test
    public void getCreatesUdpSender() throws Exception {
        final MetricsInfluxDbReporterConfiguration configuration = new MetricsInfluxDbReporterConfiguration() {
            @Override
            public URI getUri() {
                return URI.create("udp://127.0.0.1:8086/database");
            }
        };
        final InfluxDbSenderProvider provider = new InfluxDbSenderProvider(configuration);
        final InfluxDbSender influxDbSender = provider.get();
        assertTrue(influxDbSender instanceof InfluxDbUdpSender);
    }

    @Test
    public void getCreatesSenderWithCorrectDatabaseName() throws Exception {
        final MetricsInfluxDbReporterConfiguration configuration = new MetricsInfluxDbReporterConfiguration() {
            @Override
            public URI getUri() {
                return URI.create("udp://127.0.0.1:8086/data_base_1");
            }
        };
        final InfluxDbSenderProvider provider = new InfluxDbSenderProvider(configuration);
        final InfluxDbSender influxDbSender = provider.get();

        final Field field = Class.forName("com.izettle.metrics.influxdb.InfluxDbBaseSender").getDeclaredField("influxDbWriteObject");
        field.setAccessible(true);
        final InfluxDbWriteObject influxDbWriteObject = (InfluxDbWriteObject) field.get(influxDbSender);
        assertEquals("data_base_1", influxDbWriteObject.getDatabase());
    }

    @Test
    public void getWithInvalidDatabaseName() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid database name \"1database\"");

        final MetricsInfluxDbReporterConfiguration configuration = new MetricsInfluxDbReporterConfiguration() {
            @Override
            public URI getUri() {
                return URI.create("udp://127.0.0.1:8086/1database");
            }
        };
        final InfluxDbSenderProvider provider = new InfluxDbSenderProvider(configuration);
        provider.get();
    }
}