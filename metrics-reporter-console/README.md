# metrics-reporter-console

## Configuration settings

| Setting                           | Default        | Description                                                      |
| --------------------------------- | -------------- | ---------------------------------------------------------------- |
| `metrics_console_enabled`         | `false`        | Whether to start the metrics reporter.                           |
| `metrics_console_report_interval` | `15s`          | How often to report the metrics.                                 |
| `metrics_console_output`          | `stdout`       | The stream in which to write the metrics (`stdout` or `stderr`). |
| `metrics_console_locale`          | System default | Which locale to use for printing the metrics.                    |
| `metrics_console_timezone`        | System default | Which time zone to use for printing the metrics.                 |
| `metrics_console_unit_rates`      | `seconds`      | The time unit used for rates.                                    |
| `metrics_console_unit_durations`  | `milliseconds` | The time unit used for durations.                                |
| `metrics_console_include_metrics` | `.*`           | A comma-separated list of metric names to report.                |
