# metrics-reporter-cloudwatch

## Configuration settings

| Setting                                     | Default          | Description                                                                                               |
| ------------------------------------------- | ---------------- | --------------------------------------------------------------------------------------------------------- |
| `metrics_cloudwatch_enabled`                | `false`          | Whether to start the metrics reporter.|
| `metrics_cloudwatch_report_interval`        | `15s`            | How often to report the metrics to Elasticsearch.|
| `metrics_cloudwatch_namespace`              | `Graylog`        | The name of the namespace of CloudWatch to write to.|
| `metrics_cloudwatch_timestamp_local`        | `false`          | True to use the local timestamp.|
| `metrics_cloudwatch_dimensions`             | [empty]          | Defines the dimension to save in cloudtrail in the format `dimA=dim dimB=otherDim dimC=otherDim`|
| `metrics_cloudwatch_unit_rates`             | `seconds`        | The time unit used for rates.|
| `metrics_cloudwatch__unit_durations`        | `milliseconds`   | The time unit used for durations.|
| `metrics_cloudwatch__include_metrics`       | `.*`             | A comma-separated list of metric names to report.|
