# gRPC

## Summary

> gRPC is an open source remote procedure call (RPC) system initially developed at Google. It uses HTTP/2 for transport, Protocol Buffers as the interface description language, and provides features such as authentication, bidirectional streaming and flow control, blocking or nonblocking bindings, and cancellation and timeouts. It generates cross- platform client and server bindings for many languages. — wikipedia

The main use case is for machine- to-machine communication. Google is currently migrating all their internal microservices to communicate via gRPC because the protocol provides a uniform interface for defining authentication and load balancing. As well, it also is more efficient communication protocol compared to REST. There is support for mobile and the web, however, these use cases are not as widely used. It supports both request-response and streaming style communication.

## Design Strategy

- Define types and procedures
- Define service methods
- Create service definition
- Incorporate into build

## Comparison to REST


## Key Concepts

## Authentication &amp; Authorization

## Type System

## Tech Stack

## Challenges

- Using Vert.x, Kotlin, gRPC, Gradle was challenging due to lack of experience. The Vert.x documentation appears to be out dated and the Gradle configuration is off. It's recommended to consult the gRPC.io Kotlin examples.
- The Vert.x documentation does not mention that the Java plugin must be used with the Gradle configuration. Without this, the gRPC generated code will fail on the compileJava step of Kotlin.

## Conclusions

## Unanswered Questions

## Resources

- [gRPC.io](https://grpc.io/docs/)
- vert.x gRPC
  - [Docs](https://vertx.io/docs/vertx-grpc/java/)
  - [Examples](https://github.com/vert-x3/vertx-examples/tree/master/grpc-examples)
- [Kotlin gRPC Gradle build file](https://github.com/grpc/grpc-kotlin/blob/master/examples/build.gradle)

