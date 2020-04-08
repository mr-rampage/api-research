# MQTT Stack

Docker based technology stack that gets you up and running fast to manage IoT
devices in your infrastructure.

## TICK

- Docker
- Mosquitto as MQTT Broker
- Telegraf - data collection and reporting agent
- Influxdb - time series database
- Chronograf - administrative and visualization UI
- Kapacitor (optional) - data processing engine

## Tooling

Install `InfluxDB` on your workstation. Use the `influx` command to launch the
InfluxDB client.

Install `Mosquitto` on your workstation. Use `mosquitto_sub` to subscribe to a
topic and `mosquitto_pub` to publish to a topic. On some Linux distros, the
server and client are separate installations. For Ubuntu, install 
`mosquitto-clients`.

## Commands

### MQTT

Sending an Influx message to telegraf

```bash
mosquitto_pub -h localhost -p 1883 -t my-topic -m "temp,site=room value=30"
```

Subscribing to MQTT broker

```bash
mosquitto_sub -host localhost -t my-topic
```

## InfluxDB

```sql
-- List databases
SHOW DATABASES

-- List tables
SHOW MEASUREMENTS

-- Query data
SELECT * FROM sensors
```
