# gRPC

## Summary

> gRPC is an open source remote procedure call (RPC) system initially developed at Google. It uses HTTP/2 for transport, 
> Protocol Buffers as the interface description language, and provides features such as authentication, bidirectional 
> streaming and flow control, blocking or nonblocking bindings, and cancellation and timeouts. It generates cross-platform 
> client and server bindings for many languages. — wikipedia

The main use case is for machine-to-machine communication. Google is currently migrating all their internal microservices 
to communicate via gRPC because the protocol provides a uniform interface for defining authentication and load balancing. 
As well, it also is more efficient communication protocol compared to REST. There is support for mobile and the web, 
however, these use cases are not as widely used. It supports both request-response and streaming style communication.

## Design Strategy

- Define types and procedures
- Define service methods
- Create service definition
- Incorporate into build

## Comparison to REST

- It's a framework, not an architecture
- Messages are encoded and are not human readable
- Client stubs are type safe and integrates well with IDEs* (Kotlin & Java = good!, JavaScript = bad!)
- Bi-directional communication support
- Multiple resources can be batched in a single request
- Message validation is automatically done*
- Browser support is limited due to required support for http/2
- Mobile clients are available - consider using for mobile apps that deploy in countries with expensive data rates

## Comparison to MQTT

Google demonstrated using gRPC with IOT devices, but gRPC is missing the queuing mechanisms and quality of service
features of MQTT. It's probably not a replacement for MQTT.

## Key Concepts

- Service Definition - definition file for describing the service interface and the structure of the messages.
  Kind of like a Swagger file.
- Service methods
  - Unary RPC - Response-request messaging
  - Server Streaming RPC - Responses to requests are streamed back to the client, order guaranteed
  - Client Streaming RPC - Requests are streamed to the server, responses sent back to the client, order guaranteed
  - Bidirectional Streaming RPC - Response-requests are streamed, order guaranteed. However, server can define to 
    respond as a batch or respond per message.
- Channels - the mechanism for a client to connect to a gRPC server. Channels have state (i.e. am I connected, am I idle)

## Authentication &amp; Authorization

Authentication is done with standard mechanisms such as tokens or certificates. Authorization is handled within the
service call. Some frameworks, like .NET, expose annotations similar to Spring Security that can apply authorization
to a service call.

## Type System

gRPC has a fairly complete [type system][ts-1]. However, it should be noted that when using a dynamically typed 
language, like Javascript, validation must be done before hand. To reduce the amount of boilerplate code, the library 
[Protobuf.js][ts-2].

[ts-1]: https://developers.google.com/protocol-buffers/docs/proto3
[ts-2]: https://github.com/protobufjs/protobuf.js

## Tech Stack

Pretty much all tooling for gRPC are available on their [Github repository](https://github.com/grpc) for the languages
we use - Kotlin, Java, and Javascript.

## Challenges

- Using Vert.x, Kotlin, gRPC
  - Vert.x only works with version 1.25.0 of the gRPC. Any version higher would not compile.
  - Java 8+ would require a dependency to "javax.annotation:javax.annotation-api:1.2".
- IntelliJ flagged the generated code as invalid time to time. It could be an issue with the IDE setup.
- Due to the lack of a type system with JavaScript, the gRPC clients do not have IDE auto-completion. Typescript types
  are not auto-generated either. There are tools for Typescript by third-parties, but they have certain constraints.

## Conclusions

The documentation and examples provided by gRPC are excellent. This provides a single source of truth when it comes to
development. Development using a type safe language was straight forward and easy to work with. It felt very similar to
GraphQL development. However, gRPC isn't as well supported on the JavaScript side with the lack of Typescript types to
aide in development. It is most likely that as the tooling matures, Typescript support will be supported.

## Unanswered Questions

- Since this is a streaming library, it would be good to investigate it's usage with ReactiveX and Spring Reactor.
- How does one handle multiple resource requests? This looks like a candidate for a Saga pattern.
- What does mobile development look like using gRPC? Communication would be optimize, but does deserialization of the
  message cost anything to performance?
- IoT was mentioned as a possible use case, how does this work with embedded devices?

## Resources

- [gRPC.io](https://grpc.io/docs/)
- vert.x gRPC
  - [Docs](https://vertx.io/docs/vertx-grpc/java/)
  - [Examples](https://github.com/vert-x3/vertx-examples/tree/master/grpc-examples)
- [Kotlin gRPC Gradle build file](https://github.com/grpc/grpc-kotlin/blob/master/examples/build.gradle)
- [Efficient IoT with Protocol Buffers](https://medium.com/grpc/efficient-iot-with-the-esp8266-protocol-buffers-grafana-go-and-kubernetes-a2ae214dbd29)
