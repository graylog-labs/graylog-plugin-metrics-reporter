# metrics-reporter-elasticsearch

## Configuration settings

| Setting                                     | Default          | Description                                                                                               |
| ------------------------------------------- | ---------------- | --------------------------------------------------------------------------------------------------------- |
| `metrics_elasticsearch_enabled`             | `false`          | Whether to start the metrics reporter.                                                                    |
| `metrics_elasticsearch_report_interval`     | `15s`            | How often to report the metrics to Elasticsearch.                                                         |
| `metrics_elasticsearch_hosts`               | `localhost:9200` | A comma-separated list of Elasticsearch hosts to connect to in the format `hostname:port, hostname:port`. |
| `metrics_elasticsearch_index`               | `metrics`        | The name of the Elasticsearch index to write to.                                                          |
| `metrics_elasticsearch_bulk_size`           | `2500`           | Defines how many metrics are sent per bulk requests.                                                      |
| `metrics_elasticsearch_connect_timeout`     | `5s`             | The HTTP connect timeout.                                                                                 |
| `metrics_elasticsearch_timestamp_fieldname` | `@timestamp`     | The field name of the timestamp.                                                                          |
| `metrics_elasticsearch_index_date_format`   | `yyyy-MM`        | The date format to make sure to rotate to a new index.                                                    |
| `metrics_elasticsearch_additional_fields`   | [empty]          | Additional fields for Elasticsearch in the format `key:value, key:value`.                                 |
| `metrics_elasticsearch_prefix`              | [empty]          | The prefix used for all metrics.                                                                          |
| `metrics_elasticsearch_unit_rates`          | `seconds`        | The time unit used for rates.                                                                             |
| `metrics_elasticsearch_unit_durations`      | `milliseconds`   | The time unit used for durations.                                                                         |
| `metrics_elasticsearch_include_metrics`     | `.*`             | A comma-separated list of metric names to report.                                                         |
