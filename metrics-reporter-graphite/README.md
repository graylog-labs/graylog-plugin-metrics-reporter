# metrics-reporter-graphite

## Configuration settings

| Setting                              | Default          | Description                                                          |
| ------------------------------------ | ---------------- | -------------------------------------------------------------------- |
| `metrics_graphite_enabled`           | `false`          | Whether to start the metrics reporter.                               |
| `metrics_graphite_report_interval`   | `15s`            | How often to report metrics to Graphite.                             |
| `metrics_graphite_address`           | `127.0.0.1:2003` | The network address of Graphite.                                     |
| `metrics_graphite_protocol`          | `TCP`            | The protocol used to send data to Graphite (`TCP`, `UDP`, `PICKLE`). |
| `metrics_graphite_pickle_batch_size` | `100`            | The batch size when using the Pickle protocol.                       |
| `metrics_graphite_charset`           | `UTF-8`          | The charset used to send data to Graphite.                           |
| `metrics_graphite_prefix`            | [empty]          | The prefix used for all metrics.                                     |
| `metrics_graphite_unit_rates`        | `seconds`        | The time unit used for rates.                                        |
| `metrics_graphite_unit_durations`    | `milliseconds`   | The time unit used for durations.                                    |
| `metrics_graphite_include_metrics`   | `.*`             | A comma-separated list of metric names to report.                    |
