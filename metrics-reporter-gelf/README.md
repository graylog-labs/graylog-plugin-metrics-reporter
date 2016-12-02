# metrics-reporter-gelf

## Configuration settings

| Setting                                      | Default           | Description                                                    |
| -------------------------------------------- | ----------------- | -------------------------------------------------------------- |
| `metrics_gelf_enabled`                       | `false`           | Whether to start the metrics reporter.                         |
| `metrics_gelf_transport`                     | `UDP`             | The GELF transport protocol (`UDP`, `TCP`).                    |
| `metrics_gelf_host`                       | `localhost:12201` | The network address of Graylog.                                |
| `metrics_gelf_level`                         | `INFO`            | The GELF message level used for metrics.                       |
| `metrics_gelf_source`                        | `metrics`         | The name of the source of the GELF messages.                   |
| `metrics_gelf_prefix`                        | [empty]           | The prefix for all metrics.                                    |
| `metrics_gelf_report_interval`               | `15s`             | How often to report metrics to Graylog.                        |
| `metrics_gelf_unit_rates`                    | `seconds`         | The time unit used for rates.                                  |
| `metrics_gelf_unit_durations`                | `milliseconds`    | The time unit used for durations.                              |
| `metrics_gelf_include_metrics`               | `.*`              | A comma-separated list of metric names to report.              |
| `metrics_gelf_tls_enabled`                   | `false`           | Enable TLS for transport (only with `TCP`).                    |
| `metrics_gelf_tls_cert_verification_enabled` | `false`           | Enable TLS certificate verification.                           |
| `metrics_gelf_tls_trust_cert_chain_file`     | [empty]           | The trust certificate chain file for for TLS.                  |
| `metrics_gelf_connect_timeout`               | `1s`              | The connection timeout for TCP connections.                    |
| `metrics_gelf_reconnect_delay`               | `500ms`           | The time to wait between reconnects.                           |
| `metrics_gelf_queue_size`                    | `500`             | The size of the internally used queue.                         |
| `metrics_gelf_send_buffer_size`              | `-1`              | The size of the socket send buffer in bytes (`-1` = disabled). |
| `metrics_gelf_max_in_flight_sends`           | `512`             | The maximum of queued network operations.                      |
| `metrics_gelf_tcp_keep_alive`                |  `false`          | Whether to use TCP keepalive for TCP connections.              |
| `metrics_gelf_tcp_no_delay`                  |  `false`          | Use Nagle's algorithm for TCP connections.                     |
