# metrics-reporter-influxdb

## Configuration settings

| Setting                              | Default        | Description                                                                               |
| ------------------------------------ | -------------- | ----------------------------------------------------------------------------------------- |
| `metrics_influxdb_enabled`           | `false`        | Whether to start the metrics reporter.                                                    |
| `metrics_influxdb_report_interval`   | `15s`          | How often to report the metrics to InfluxDB.                                              |
| `metrics_influxdb_unit_rates`        | `seconds`      | The time unit used for rates.                                                             |
| `metrics_influxdb_unit_durations`    | `milliseconds` | The time unit used for durations.                                                         |
| `metrics_influxdb_uri`               | [empty]        | The URI to the InfluxDB server in the format `http://[user:password]@host:port/database`. |
| `metrics_influxdb_time_precision`    | `seconds`      | The time precision used for InfluxDB.                                                     |
| `metrics_influxdb_connect_timeout`   | `5s`           | The HTTP connect timeout.                                                                 |
| `metrics_influxdb_read_timeout`      | `5s`           | The HTTP read timeout.                                                                    |
| `metrics_influxdb_tags`              | [empty]        | Additional tags for InfluxDB in the format `key:value, key:value`.                        |
| `metrics_influxdb_group_gauges`      | `false`        | Whether to group gauges.                                                                  |
| `metrics_influxdb_skip_idle_metrics` | `true`         | Whether to skip idle metrics.                                                             |
