# metrics-reporter-mongodb

## Configuration settings

| Setting                              | Default        | Description                                                         |
| ------------------------------------ | -------------- | ------------------------------------------------------------------- |
| `metrics_mongodb_enabled`           | `false`        | Whether to start the metrics reporter.                               |
| `metrics_mongodb_uri`               | [empty]        | The URI to the MongoDB server in the [Connection String URI format]. |
| `metrics_mongodb_additional_fields` | [empty]        | Additional fields for MongoDB in the format `key:value, key:value`.  |
| `metrics_mongodb_report_interval`   | `15s`          | How often to report the metrics to MongoDB.                          |
| `metrics_mongodb_unit_rates`        | `seconds`      | The time unit used for rates.                                        |
| `metrics_mongodb_unit_durations`    | `milliseconds` | The time unit used for durations.                                    |

[Connection String URI format]:https://docs.mongodb.com/manual/reference/connection-string/