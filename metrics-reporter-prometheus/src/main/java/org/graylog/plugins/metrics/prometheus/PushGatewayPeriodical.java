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
package org.graylog.plugins.metrics.prometheus;

import com.google.common.primitives.Ints;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.PushGateway;
import org.graylog2.plugin.periodical.Periodical;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;

import static java.util.Objects.requireNonNull;

public class PushGatewayPeriodical extends Periodical {
    private static final Logger LOG = LoggerFactory.getLogger(PushGatewayPeriodical.class);

    private final MetricsPrometheusReporterConfiguration configuration;
    private final CollectorRegistry collectorRegistry;
    private final PushGateway pushGateway;

    @Inject
    public PushGatewayPeriodical(MetricsPrometheusReporterConfiguration configuration,
                                 CollectorRegistry collectorRegistry,
                                 PushGateway pushGateway) {
        this.configuration = requireNonNull(configuration);
        this.collectorRegistry = requireNonNull(collectorRegistry);
        this.pushGateway = requireNonNull(pushGateway);
    }

    @Override
    public void doRun() {
        try {
            pushGateway.push(
                    collectorRegistry,
                    configuration.getJobName(),
                    configuration.getGroupingKey());
        } catch (IOException e) {
            LOG.error("Error while sending metrics to Prometheus Pushgateway", e);
        }
    }

    @Override
    public boolean runsForever() {
        return false;
    }

    @Override
    public boolean stopOnGracefulShutdown() {
        return true;
    }

    @Override
    public boolean masterOnly() {
        return false;
    }

    @Override
    public boolean startOnThisNode() {
        return configuration.isEnabled();
    }

    @Override
    public boolean isDaemon() {
        return true;
    }

    @Override
    public int getInitialDelaySeconds() {
        return 0;
    }

    @Override
    public int getPeriodSeconds() {
        return Ints.saturatedCast(configuration.getReportInterval().toSeconds());
    }

    @Override
    protected Logger getLogger() {
        return LOG;
    }
}
