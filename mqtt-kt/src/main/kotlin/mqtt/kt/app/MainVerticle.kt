package mqtt.kt.app

import io.vertx.core.AbstractVerticle
import io.vertx.mqtt.MqttClient
import io.vertx.mqtt.MqttClientOptions
import java.nio.charset.Charset.defaultCharset

class MainVerticle : AbstractVerticle() {
  override fun start() {
    val client = MqttClient.create(vertx, MqttClientOptions().setClientId("Vertx"))
    val topic = "sensors"

    client.publishHandler { publish ->
      println("Just received message on [${publish.topicName()}] payload [${publish.payload().toString(defaultCharset())}] with QoS [${publish.qosLevel()}]")
    }

    client.connect(1883, "localhost") {
      if (it.succeeded()) {
        println("Successfully connected")
        client.subscribe(topic, 0)
      } else {
        println("Failed to connect")
        client.disconnect()
      }
    }

  }
}
