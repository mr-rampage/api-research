# APIs Research Week

## Abstract

The goal of this research week is to research at a high level the different APIs available today in order to understand the use cases for each API. This will allow us to make effective recommendations for clients on future projects. By the end of the research week, we should have enough knowledge to understand the tools used to implement, test, and the pros/cons of each protocol.  This should be enough to provide a certain amount of confidence in being able to recommend, learn, and use these APIs on projects that fit the appropriate use cases.

## Kotlin &amp; IntelliJ
When using Kotlin and IntelliJ, it's recommended to set the project module language to Java 8. I experienced some weird issues where generated code would be considered invalid by IntelliJ. When I changed the modules setting to Java 8 and rebuilt, all issues were resolved. Oddly enough, IntelliJ then defaulted the module language back to Java 11.

## API Styles

- Web APIs - REST vs. "REST"
- Query APIs - GraphQL
- Event-Driven APIs - Kafka, WebSub (W3C), MQTT
- RPC APIs - SOAP, gRPC
- Flat File - FTP
- P2P APIs - SSB

APIs can be a framework, language, or specification. GraphQL falls under language, whereas REST falls under specification.

## APIs to research

[Code is available on this repository](https://github.com/mr-rampage/api-research)

- GraphQL
- gRPC
- MQTT
- SSB (optional)

## Research Strategy

Each protocol will require roughly 1 to 2 days. GraphQL and gRPC will require 2 days of research, MQTT and SSB will require 1 day of research. For each API, the strategy is to:

- Identify the key properties of the protocol.
- Implement a server using the protocol and interact with it. Ideally using docker to get up quickly.
- Implement a client to interact with the protocol.
- Identify use cases that fit the properties.
- Identify testing strategies for contract testing of the API.
- Document pain points.
- Identify libraries for JavaScript, Kotlin, and Java (?) support.

## TLDW;
This video pretty much summarizes the use cases and how to choose the appropriate API for your problem.

[![IMAGE ALT TEXT](http://img.youtube.com/vi/gRZbgsmDj_0/0.jpg)](http://www.youtube.com/watch?v=gRZbgsmDj_0 "Video Title")

In summary, the use cases are:

### GraphQL

- Backend for frontends
- Short term projects
- Uncertain use cases (start ups)
- Just access to data without need for infrastructure
- Developer experience with little effort

## Findings

- [GraphQL](./docs/graphql.md)
- [gRPC](./docs/grpc.md)
- [MQTT](./docs/mqtt.md)
