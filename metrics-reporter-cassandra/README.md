# metrics-reporter-cassandra

## Configuration settings

| Setting                             | Default        | Description                                                 |
| ----------------------------------- | -------------- | ----------------------------------------------------------- |
| `metrics_cassandra_enabled`         | `false`        | Whether to start the metrics reporter.                      |
| `metrics_cassandra_report_interval` | `15s`          | How often to report the metrics to Cassandra.               |
| `metrics_cassandra_unit_rates`      | `seconds`      | The time unit used for rates.                               |
| `metrics_cassandra_unit_durations`  | `milliseconds` | The time unit used for durations.                           |
| `metrics_cassandra_prefix`          | [empty]        | The prefix used for all metrics.                            |
| `metrics_cassandra_addresses`       | `127.0.0.1`    | A comma-separated list of contact points for Cassandra.     |
| `metrics_cassandra_port`            | `9042`         | The port to connect to the Cassandra Native Transport with. |
| `metrics_cassandra_keyspace`        | `graylog`      | The Cassandra keyspace to connect to.                       |
| `metrics_cassandra_table`           | `metrics`      | The Cassandra table to write metrics into.                  |
| `metrics_cassandra_ttl`             | `60`           | The time-to-live for the Cassandra connection.              |
| `metrics_cassandra_consistency`     | `ONE`          | The [consistency level][1] used to write metrics.           |

[1]: https://docs.datastax.com/en/cassandra/3.x/cassandra/dml/dmlConfigConsistency.html