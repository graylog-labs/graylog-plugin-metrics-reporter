/**
 * This file is part of Graylog Metrics Ganglia Reporter Plugin.
 *
 * Graylog Metrics Ganglia Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics Ganglia Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics Ganglia Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.ganglia.providers;

import com.google.common.base.Throwables;
import info.ganglia.gmetric4j.gmetric.GMetric;
import org.graylog.plugins.metrics.ganglia.MetricsGangliaReporterConfiguration;

import javax.inject.Inject;
import javax.inject.Provider;
import java.io.IOException;

import static java.util.Objects.requireNonNull;

public class GMetricProvider implements Provider<GMetric> {
    private final MetricsGangliaReporterConfiguration configuration;

    @Inject
    public GMetricProvider(MetricsGangliaReporterConfiguration configuration) {
        this.configuration = requireNonNull(configuration);
    }

    @Override
    public GMetric get() {
        try {
            return new GMetric(
                    configuration.getGroup(),
                    configuration.getPort(),
                    configuration.getUDPAddressingMode(),
                    configuration.getTtl(),
                    configuration.isGanglia31x(),
                    configuration.getUUID(),
                    configuration.getSpoof());
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }
}
