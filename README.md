# Graylog Metrics Reporter plugins

## ATTENTION: This repository is no longer maintained and has been archived. Please check the [metrics documentation](https://docs.graylog.org/docs/metrics) for available metric exporters. Graylog server has a built-in Prometheus exporter.

[![Github Downloads](https://img.shields.io/github/downloads/graylog-labs/graylog-plugin-metrics-reporter/total.svg)](https://github.com/graylog-labs/graylog-plugin-metrics-reporter/releases)
[![GitHub Release](https://img.shields.io/github/release/graylog-labs/graylog-plugin-metrics-reporter.svg)](https://github.com/graylog-labs/graylog-plugin-metrics-reporter/releases)
[![Build Status](https://travis-ci.org/graylog-labs/graylog-plugin-metrics-reporter.svg?branch=master)](https://travis-ci.org/graylog-labs/graylog-plugin-metrics-reporter)

A collection of plugins for reporting internal Graylog metrics to other systems.

## Installation

Put the JAR file of the desired metrics reporter plugin into the Graylog plugin directory of each Graylog node and add the relevant configuration settings to their configuration files.

After installing the metrics reporter plugin and adding the configuration settings, Graylog needs to be restarted.

Specific settings for each metrics reporter plugin have been documented in the respective README files:

* [metrics-reporter-cassandra](metrics-reporter-cassandra/README.md)
* [metrics-reporter-cloudwatch](metrics-reporter-cloudwatch/README.md)
* [metrics-reporter-console](metrics-reporter-console/README.md)
* [metrics-reporter-csv](metrics-reporter-csv/README.md)
* [metrics-reporter-datadog](metrics-reporter-datadog/README.md)
* [metrics-reporter-elasticsearch](metrics-reporter-elasticsearch/README.md)
* [metrics-reporter-ganglia](metrics-reporter-ganglia/README.md)
* [metrics-reporter-gelf](metrics-reporter-gelf/README.md)
* [metrics-reporter-graphite](metrics-reporter-graphite/README.md)
* [metrics-reporter-influxdb](metrics-reporter-influxdb/README.md)
* [metrics-reporter-jmx](metrics-reporter-jmx/README.md)
* [metrics-reporter-librato](metrics-reporter-librato/README.md)
* [metrics-reporter-mongodb](metrics-reporter-mongodb/README.md)
* [metrics-reporter-opentsdb](metrics-reporter-opentsdb/README.md)
* [metrics-reporter-prometheus](metrics-reporter-prometheus/README.md)
* [metrics-reporter-slf4j](metrics-reporter-slf4j/README.md)
* [metrics-reporter-statsd](metrics-reporter-statsd/README.md)



## Development

This project is using Maven 3 and requires Java 8 or higher. The plugin will require Graylog 3.0.0 or higher.

* Clone this repository.
* Run `mvn package` to build JAR files of all plugins.
* Optional: Run `mvn jdeb:jdeb` and `mvn rpm:rpm` to create a DEB and RPM package respectively.
* Copy generated JAR file(s) from the "target" directory to your Graylog server plugin directory.
* Restart the Graylog server.


## License

Copyright (c) 2016 Graylog, Inc.

This library is licensed under the GNU General Public License, Version 3.0.

See https://www.gnu.org/licenses/gpl-3.0.html or the LICENSE.txt file in this repository for the full license text.
