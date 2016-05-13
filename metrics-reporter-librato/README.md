# metrics-reporter-librato

## Configuration settings

| Setting                               | Default        | Description                                 |
| ------------------------------------- | -------------- | ------------------------------------------- |
| `metrics_librato_enabled`             | `false`        | Whether to start the metrics reporter.      |
| `metrics_librato_report_interval`     | `15s`          | How often to report metrics to Librato.     |
| `metrics_librato_username`            | [empty]        | The username used for librato.              |
| `metrics_librato_token`               | [empty]        | The token used for Librato.                 |
| `metrics_librato_source`              | [empty]        | The source used for Librato                 |
| `metrics_librato_name`                | `Graylog`      | The name used for Librato.                  |
| `metrics_librato_prefix`              | [empty]        | The prefix used for all metrics.            |
| `metrics_librato_prefix_delimiter`    | `.`            | The prefix delimiter used for all metrics.  |
| `metrics_librato_timeout`             | `5s`           | The timeout for communicating with Librato. |
| `metrics_librato_delete_idle_stats`   | `true`         | Whether to delete idle stats.               |
| `metrics_librato_omit_complex_gauges` | `false`        | Whether to omit complex gauges.             |
| `metrics_librato_source_regex`        | [empty]        | The source regex used for Librato.          |
| `metrics_librato_unit_rates`          | `seconds`      | The time unit used for rates.               |
| `metrics_librato_unit_durations`      | `milliseconds` | The time unit used for durations.           |
