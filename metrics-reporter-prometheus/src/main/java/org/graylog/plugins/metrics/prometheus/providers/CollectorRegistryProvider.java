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

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.dropwizard.DropwizardExports;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import static java.util.Objects.requireNonNull;

@Singleton
public class CollectorRegistryProvider implements Provider<CollectorRegistry> {
    private final DropwizardExports dropwizardExports;

    @Inject
    public CollectorRegistryProvider(DropwizardExports dropwizardExports) {
        this.dropwizardExports = requireNonNull(dropwizardExports);
    }

    @Override
    public CollectorRegistry get() {
        final CollectorRegistry collectorRegistry = new CollectorRegistry();
        collectorRegistry.register(dropwizardExports);

        return collectorRegistry;
    }
}
