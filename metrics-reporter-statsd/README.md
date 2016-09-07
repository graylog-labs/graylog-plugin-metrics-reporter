# metrics-reporter-statsd

## Configuration settings

| Setting                          | Default          | Description                                       |
| -------------------------------- | ---------------- | ------------------------------------------------- |
| `metrics_statsd_enabled`         | `false`          | Whether to start the metrics reporter.            |
| `metrics_statsd_address`         | `localhost:8125` | The network address of statsd.                    |
| `metrics_statsd_prefix`          | [empty]          | The prefix for all metrics.                       |
| `metrics_statsd_report_interval` | `15s`            | How often to report metrics to statsd.            |
| `metrics_statsd_unit_rates`      | `seconds`        | The time unit used for rates.                     |
| `metrics_statsd_unit_durations`  | `milliseconds`   | The time unit used for durations.                 |
| `metrics_statsd_include_metrics` | `.*`             | A comma-separated list of metric names to report. |
