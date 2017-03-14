# metrics-reporter-opentsdb

## Configuration settings

| Setting                                      | Default                  | Description                                                                       |
| -------------------------------------------- | ------------------------ | --------------------------------------------------------------------------------- |
| `metrics_opentsdb_enabled`                   | `false`                  | Whether to start the metrics reporter.                                            |
| `metrics_opentsdb_protocol`                  | `TELNET`                 | Network protocol of the OpenTSDB input (`TELNET` or `HTTP`).                      |
| `metrics_opentsdb_telnet_address`            | `localhost:4242`         | Network address of the OpenTSDB Telnet input.                                     |
| `metrics_opentsdb_http_base_url`             | `http://localhost:4242/` | Base URL of the OpenTSDB HTTP input.                                              |
| `metrics_opentsdb_http_connect_timeout`      | `5000`                   | HTTP connect timeout in milliseconds.                                             |
| `metrics_opentsdb_http_read_timeout`         | `5000`                   | HTTP read timeout in milliseconds.                                                |
| `metrics_opentsdb_http_enable_gzip`          | `true`                   | Enable compression of HTTP payload with gzip.                                     |
| `metrics_opentsdb_batch_size`                | `0`                      | How many metrics to send in a batch (`0` means no limit).                         |
| `metrics_opentsdb_counter_gauge_decorations` | `true`                   | Decorate names of counters ("count") and gauges ("value").                        |
| `metrics_opentsdb_tags`                      | [empty]                  | List of tags (k:v) sent along with the metrics, e. g. `tag1:value1, tag2:value2`. |
| `metrics_opentsdb_prefix`                    | [empty]                  | The prefix for all metrics.                                                       |
| `metrics_opentsdb_report_interval`           | `15s`                    | How often to report metrics to openTsdb.                                          |
| `metrics_opentsdb_unit_rates`                | `seconds`                | The time unit used for rates.                                                     |
| `metrics_opentsdb_unit_durations`            | `milliseconds`           | The time unit used for durations.                                                 |
| `metrics_opentsdb_include_metrics`           | `.*`                     | A comma-separated list of metric names to report.                                 |
