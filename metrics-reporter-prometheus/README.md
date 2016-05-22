# metrics-reporter-prometheus

## Pulling metrics

The Prometheus metrics endpoint is available at `http://graylog.example.com:12900/plugins/org.graylog.plugins.metrics.prometheus/metrics`.

The corresponding configuration in `prometheus.yml` would look like the following code snippet:

```yaml
# See https://prometheus.io/docs/operating/configuration/#<scrape_config> for details
scrape_configs:
  - job_name: 'graylog'
    scrape_interval: 15s
    metrics_path: '/plugins/org.graylog.plugins.metrics.prometheus/metrics'
    basic_auth:
      username: graylog_username
      password: graylog_password
    # Optional TLS configuration; see https://prometheus.io/docs/operating/configuration/#<tls_config>
    #tls_config:
    target_groups:
      - targets: ['graylog1.example.com:12900', 'graylog2.example.com:12900']
```

## Configuration settings

| Setting                              | Default          | Description                                                                                       |
| ------------------------------------ | ---------------- | ------------------------------------------------------------------------------------------------- |
| `metrics_prometheus_enabled`         | `false`          | Whether to push the metrics to a [Prometheus Pushgateway].                                        |
| `metrics_prometheus_report_interval` | `15s`            | How often to report metrics to the [Prometheus Pushgateway].                                      |
| `metrics_prometheus_address`         | `127.0.0.1:9091` | The network address of the [Prometheus Pushgateway].                                              |
| `metrics_prometheus_job_name`        | `graylog`        | The job name used with the [Prometheus Pushgateway].                                              |
| `metrics_prometheus_grouping_key`    | [empty]          | The grouping key used with the [Prometheus Pushgateway] in the format `key1:value1, key2:value2`. |

[Prometheus Pushgateway]: https://github.com/prometheus/pushgateway