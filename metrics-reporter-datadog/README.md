# metrics-reporter-datadog

## Configuration settings

| Setting                                | Default          | Description                                                                  |
| -------------------------------------- | ---------------- | ---------------------------------------------------------------------------- |
| `metrics_datadog_enabled`              | `false`          | Whether to start the metrics reporter.                                       |
| `metrics_datadog_report_interval`      | `15s`            | How often to report metrics to Datadog.                                      |
| `metrics_datadog_prefix`               | [empty]          | The prefix used for all metrics.                                             |
| `metrics_datadog_transport`            | `HTTP`           | The transport used for sending metrics to Datadog (`HTTP`, `UDP`).           |
| `metrics_datadog_api_key`              | [empty]          | The Datadog API key.                                                         |
| `metrics_datadog_hostname`             | [empty]          | The hostname (source) used to report metrics to Datadog.                     |
| `metrics_datadog_detect_ec2_hostname`  | `false`          | Whether to try to detect the hostname of the EC2 instance.                   |
| `metrics_datadog_http_connect_timeout` | `5s`             | The connect timeout for HTTP connections to Datadog.                         |
| `metrics_datadog_http_socket_timeout`  | `5s`             | The socket timeout for HTTP connections to Datadog.                          |
| `metrics_datadog_http_proxy`           | [empty]          | The hostname and port of the HTTP proxy to use in format `example.org:8080`. |
| `metrics_datadog_udp_address`          | `localhost:8125` | The network address of the Datadog UDP receiver.                             |
| `metrics_datadog_udp_prefix`           | [empty]          | The prefix used for reporting metrics to Datadog via UDP.                    |
| `metrics_datadog_unit_rates`           | `seconds`        | The time unit used for rates.                                                |
| `metrics_datadog_unit_durations`       | `milliseconds`   | The time unit used for durations.                                            |
