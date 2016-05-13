# metrics-reporter-slf4j

## Configuration settings

| Setting                         | Default        | Description                                                            |
| ------------------------------- | -------------- | ---------------------------------------------------------------------- |
| `metrics_slf4j_enabled`         | `false`        | Whether to start the metrics reporter.                                 |
| `metrics_slf4j_report_interval` | `15s`          | How often to report metrics via SLF4J.                                 |
| `metrics_slf4j_unit_rates`      | `seconds`      | The time unit used for rates.                                          |
| `metrics_slf4j_unit_durations`  | `milliseconds` | The time unit used for durations.                                      |
| `metrics_slf4j_marker`          | [empty]        | The name of the marker used to log metrics.                            |
| `metrics_slf4j_logger`          | [empty]        | The name of the logger used to log metrics.                            |
| `metrics_slf4j_level`           | `TRACE`        | The log level used to log metrics (see [SLF4J documentation][levels]). |

[levels]: http://www.slf4j.org/apidocs/org/slf4j/event/Level.html
