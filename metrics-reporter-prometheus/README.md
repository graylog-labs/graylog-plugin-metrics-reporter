# metrics-reporter-prometheus

## Pulling metrics

The Prometheus metrics endpoint is available at `http://graylog.example.com:9000/api/plugins/org.graylog.plugins.metrics.prometheus/metrics`.

The corresponding configuration in `prometheus.yml` would look like the following code snippet:

```yaml
# See https://prometheus.io/docs/operating/configuration/#<scrape_config> for details
scrape_configs:
  - job_name: 'graylog'
    scrape_interval: 15s
    metrics_path: '/api/plugins/org.graylog.plugins.metrics.prometheus/metrics'
    basic_auth:
      username: graylog_username
      password: graylog_password
    # Optional TLS configuration; see https://prometheus.io/docs/operating/configuration/#<tls_config>
    #tls_config:
    static_configs:
      - targets: ['graylog1.example.com:9000', 'graylog2.example.com:9000']
```

## Configuration settings

| Setting                              | Default          | Description                                                                                       |
| ------------------------------------ | ---------------- | ------------------------------------------------------------------------------------------------- |
| `metrics_prometheus_enabled`         | `false`          | Whether to expose prometheus metrics.                                        |
| `metrics_prometheus_pushgateway_disabled`| `false`      | Whether to disable push metrics to a [Prometheus Pushgateway].                                        |
| `metrics_prometheus_report_interval` | `15s`            | How often to report metrics to the [Prometheus Pushgateway].                                      |
| `metrics_prometheus_address`         | `127.0.0.1:9091` | The network address of the [Prometheus Pushgateway].                                              |
| `metrics_prometheus_job_name`        | `graylog`        | The job name used with the [Prometheus Pushgateway].                                              |
| `metrics_prometheus_grouping_key`    | [empty]          | The grouping key used with the [Prometheus Pushgateway] in the format `key1:value1, key2:value2`. |

[Prometheus Pushgateway]: https://github.com/prometheus/pushgateway
