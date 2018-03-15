# metrics-reporter-influxdb

## Configuration settings

| Setting                              | Default        | Description                                                                                   |
| ------------------------------------ | -------------- | --------------------------------------------------------------------------------------------- |
| `metrics_influxdb_enabled`           | `false`        | Whether to start the metrics reporter.                                                        |
| `metrics_influxdb_report_interval`   | `15s`          | How often to report the metrics to InfluxDB.                                                  |
| `metrics_influxdb_unit_rates`        | `seconds`      | The time unit used for rates.                                                                 |
| `metrics_influxdb_unit_durations`    | `milliseconds` | The time unit used for durations.                                                             |
| `metrics_influxdb_uri`               | [empty]        | The URI to the InfluxDB server in the format `protocol://[user:password]@host:port/database`. |
| `metrics_influxdb_time_precision`    | `seconds`      | The time precision used for InfluxDB (only HTTP transport).                                   |
| `metrics_influxdb_socket_timeout`    | `5s`           | The TCP/UDP socket timeout.                                                                   |
| `metrics_influxdb_connect_timeout`   | `5s`           | The HTTP connect timeout.                                                                     |
| `metrics_influxdb_read_timeout`      | `5s`           | The HTTP read timeout.                                                                        |
| `metrics_influxdb_tags`              | [empty]        | Additional tags for InfluxDB in the format `key:value, key:value`.                            |
| `metrics_influxdb_group_gauges`      | `false`        | Whether to group gauges.                                                                      |
| `metrics_influxdb_skip_idle_metrics` | `true`         | Whether to skip idle metrics.                                                                 |
| `metrics_influxdb_include_metrics`   | `.*`           | A comma-separated list of metric names to report.                                             |


## Supported Transports

This metrics reporter plugin supports connecting to InfluxDB via [HTTP(S)][InfluxDB-HTTP], TCP, or [UDP][InfluxDB-UDP].

Example settings for `metrics_influxdb_uri`:

* `http://user:password@influx.example.org:8086/graylog`
* `tcp://influx.example.org:8089/graylog`
* `udp://influx.example.org:8089/graylog`

[InfluxDB-HTTP]: https://docs.influxdata.com/influxdb/v1.5/guides/writing_data/
[InfluxDB-UDP]: https://docs.influxdata.com/influxdb/v1.5/supported_protocols/udp/
