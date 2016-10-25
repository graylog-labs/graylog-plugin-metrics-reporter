# metrics-reporter-ganglia

## Configuration settings

| Setting                           | Default                    | Description                                                                       |
| --------------------------------- | -------------------------- | --------------------------------------------------------------------------------- |
| `metrics_ganglia_enabled`         | `false`                    | Whether to start the metrics reporter.                                            |
| `metrics_ganglia_group`           | `127.0.0.1`                | The group (IP address) used to send metrics to Ganglia.                           |
| `metrics_ganglia_spoof`           | [empty]                    | The spoof (IP:name) used to send metrics to Ganglia.                           |
| `metrics_ganglia_port`            | `8649`                     | The port used to send metrics to Ganglia.                                         |
| `metrics_ganglia_addressing_mode` | [inferred from IP address] | The UDP addressing mode used to send metrics to Ganglia (`UNICAST`, `MULTICAST`). |
| `metrics_ganglia_ttl`             | `1`                        | The TTL of Multicast packets.                                                     |
| `metrics_ganglia_version31x`      | `false`                    | Whether to use the Ganglia 3.0.x or 3.1.x protocol.                               |
| `metrics_ganglia_uuid`            | [empty]                    | The UUID used for metrics in Ganglia.                                             |
| `metrics_ganglia_dmax`            | `0`                        | The dMax used for metrics in Ganglia.                                             |
| `metrics_ganglia_tmax`            | `60`                       | The tMax used for metrics in Ganglia.                                             |
| `metrics_ganglia_report_interval` | `15s`                      | How often to report metrics to Ganglia.                                           |
| `metrics_ganglia_prefix`          | [empty]                    | The prefix used for all metrics.                                                  |
| `metrics_ganglia_unit_rates`      | `seconds`                  | The time unit used for rates.                                                     |
| `metrics_ganglia_unit_durations`  | `milliseconds`             | The time unit used for durations.                                                 |
| `metrics_ganglia_include_metrics` | `.*`                       | A comma-separated list of metric names to report.                                 |
