# metrics-reporter-csv

## Configuration settings

| Setting                       | Default        | Description                                        |
| ----------------------------- | -------------- | -------------------------------------------------- |
| `metrics_csv_enabled`         | `false`        | Whether to start the metrics reporter.             |
| `metrics_csv_report_interval` | `15s`          | How often to write the metrics into the CSV files. |
| `metrics_csv_directory`       | [empty]        | The directory in which to write the CSV files.     |
| `metrics_csv_locale`          | System default | The locale to use for writing the CSV files.       |
| `metrics_csv_unit_rates`      | `seconds`      | The time unit used for rates.                      |
| `metrics_csv_unit_durations`  | `milliseconds` | The time unit used for durations.                  |
