# MQTT

## Summary

MQTT was designed by IBM to provide sensor date for oil pipelines through a desert in the 90s. Data was sent on high
latency, low bandwidth dial up and satellite networks. For these reasons, MQTT was designed with resiliency. Given its
long history, the support for MQTT is extremely broad and well established. The MQTT protocol involves many clients
publishing messages to one or many MQTT brokers. The brokers then broadcast those messages to any clients subscribed
to any topics. MQTT uses a quality of service configuration to ensure that messages arrive at their destination.

## Design Strategy

One could build an MQTT broker in Java, Javascript, or Kotlin, but the most effective way is to use an officially
supported MQTT broker and configure it. Since the MQTT broker has a single responsibility, the need for it to be coded
and customized for a project would be pretty slim. For this research, Eclipse Mosquitto was tested as a docker container.

The clients would then publish or subscribe to a topic where custom code could handle the data.

## Comparison to REST

- Not really client/server, this is many-to-many, as many clients can publish to many brokers and the brokers can
  broadcast to many clients.
- More applicable to machine-to-machine communication
- Although, apparently Facebook [uses MQTT for its Facebook Messenger service](https://www.facebook.com/notes/facebook-engineering/building-facebook-messenger/10150259350998920),
  so it still maybe applicable for constrained environments like mobile

## Key Concepts

- Publish/Subscribe
- Messages
- Topics
- Broker

## Authentication &amp; Authorization

Authentication is done through standard practices, such as JWT. Mosquitto provides authentication and authorization
mechanisms for topics. Topics are managed through the `mosquitto_passwd` tool. The `Mosquitto Go Auth` plugin provides
alternative authentication & authorization model. Since it's written in Go, it is easier to extend and build. This 
plugin provides authentication with JWT and requires Postgresql. Since security is managed by the broker, it's seems
like an effective way for many-to-many systems to communicate in a secure fashion.

## Type System

There is no type system. Messages are sent in binary format and must be deserialized from a string.

## Tech Stack

MQTT is an established protocol and is extremely well supported in the Java, Kotlin, and Javascript ecosystems. However,
given the robustness and production use of the Mosquitto broker, it makes little sense to implement an MQTT broker in
code. There are many other established brokers, like HiveMQ.

Using the TICK stack with an MQTT broker reduces the amount of code needed to be maintained. If the customizations of
the TICK stack do not meet the needs of the project, each component could be replaced with custom code.

## Challenges

- Creating a private network with Docker Compose was challenging due to lack of knowledge - so was abandoned. However,
  in an ideal setup, only the MQTT broker and the Chronograf web server should be exposed.
- Deserializing the string could be challenging for projects. However, in this case, Telegraf provided the facilities
  to create adapters through configuration.
- Messages greater than 256 Megabytes cannot be sent.

## Conclusions

MQTT is suitable for resource constrained environments where the messages must be sent and received. The quality of
service feature aides in this and could help in use cases where the messages must be sent. It may not be suitable for
web applications, but as Facebook has shown, it could be used by web applications with the use case of messaging.

The availability of the TICK stack as docker containers makes it an ideal stack to use in the cloud. Although, this may
not be necessary as Amazon, Microsoft, and many other providers provide MQTT brokers as a service.

## Unanswered Questions

- How does it integrate with Keycloak?
- How does Kapcitor fit in with the rest of the TICK stack?
- How does this compare to Kafka? Is Kafka a more suitable solution for our line of work?

## Resources

- [InfluxData](https://www.influxdata.com/)
- [Vert.x MQTT](https://vertx.io/docs/#mqtt)
- [Aedes](https://github.com/moscajs/aedes)
- [Mqtt.js](https://github.com/mqttjs/MQTT.js)
