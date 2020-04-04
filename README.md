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

[Code is available on this repository][1]

- GraphQL
- gRPC
- MQTT
- SSB (optional)

[1]: https://github.com/mr- rampage/api-research

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

## GraphQL

### Summary

Created by Facebook, this is a query based API. It is agnostic of transfer protocol, but is seems to be mainly used over HTTP. The key concept is that a client can interact with data through a declarative manner. By defining GraphQL payloads, the GraphQL server will respond by returning data or mutating data. GraphQL also supports a reactive interface through subscriptions. GraphQL relies heavily on types and has a fairly strong type system.

### Design Strategy

- Define your types, queries, and mutations
- Implement resolvers to handle these types &amp; fields - i.e. get the data from the data source
- Repeat as new requirements arrive

### Comparisons to REST

- Solves the over/under fetching data problem
- Only a single URL
- Introspection - clients can ask the server for the schema (i.e. OpenAPI, but built in)

### Key Concepts

- Queries
  - Supports destructuring of fragments to reduce boilerplate
- Mutations
- Subscriptions
- Resolver
  - Batch Resolving
  - Queries are resolved by walking the graph
  - One pattern of authorization happens here

### Authentication &amp; Authorization

Authentication is handled with standard web technologies (i.e. JWT, OAuth, Cookies, etc...). Starting the Apollo server, authentication can then be handled by the context property of Apollo. Authorization can be handled in the resolver by delegating to a business logic layer or module. Another pattern is to use high- order functions to construct the resolvers and inject the authorization logic within each resolver. See resources for examples.

### Type System

- Scalars: String, Int, Float, Boolean, ID
- Object
- Enum
- Interfaces
- Union Types (i.e. union Person = Adult | Child) - conditional fragments used to query
- Custom Types (i.e. Date)

### Tech Stack

#### JavaScript
- [GRANDstack](https://grandstack.io) - starter with GraphQL, React, Apollo, and Neo4j
- [GraphQL Yoga](https://github.com/prisma-labs/graphql-yoga) - kind of like &quot;spring boot&quot; for graphql and node.js. Built on express.js &amp; apollo-server

#### Kotlin
- [ExpediaGroup](https://github.com/ExpediaGroup/graphql-kotlin) - starter with GraphQL, Kotlin, Spring Boot
- [Vert.x](https://vertx.io) - starter with GraphQL support under Technical Review

#### Java

- [GraphQL Spring Kickstart](https://github.com/graphql-java-kickstart/graphql-spring-boot) - starter with GraphQL, Java, Spring Boot

### Challenges

- No caching since queries could be anything
- Vulnerable to DDOS attacks - although there are documented best practices to avoid DDOS

### Conclusions

The Javascript side seems more suited for GraphQL as the Apollo server automates much of the work involved in creating a GraphQL server using the GRANDstack. On the Kotlin/Java side, there is a bit more work involved in wiring up the GraphQL schema to the appropriate mutations, queries, and subscriptions. Compared to Vert.x's OpenAPI support, the GraphQL is lacking in terms of the automated code generation (i.e. wiring and models). However, this seems to support the use case of using GraphQL of a backend for frontend. If one was to use GraphQL to support a single page application, it would make sense for this to be written in Node.js.

A possible recommendation is to start a project using GraphQL on Node.js to get develop up and running quickly. The UI and GraphQL server could evolve independently and data could be gathered to understand how clients use the data. Afterwards, when there is an understanding of how the data is used, a REST API could be crafted to reflect these use cases and the Apollo client/server could be deprecated and replaced with a REST client/server.

The introspection feature of GraphQL makes it really easy to work with and to understand what data/queries are available.

### Unanswered Questions

- How are migration scripts managed for Neo4j? (Not relevant because Neo4j could be replaced with PostgreSQL)
- Why does GraphQL &amp; Spring Boot require [so much code](https://github.com/expediagroup/graphql-kotlin/tree/master/examples/spring/src/main/kotlin/com/expediagroup/graphql/examples)?
- Why doesn't GraphQL in Java/Kotlin don't seem to understand the "@relation" directive?
- How does GraphQL perform on a relational database? GraphQL operates by "walking" the graph when resolving a query. This makes it optimal for graph databases.

### Resources

- [How to GraphQL](https://www.howtographql.com/basics/0-introduction/)
- [Authorization in GraphQL](https://blog.apollographql.com/authorization-in-graphql-452b1c402a9)
- [Spring GraphQL Tutorial](https://www.baeldung.com/spring-graphql)
- [Tutorials for most languages](https://www.howtographql.com/choose/)

## gRPC

### Summary

> gRPC is an open source remote procedure call (RPC) system initially developed at Google. It uses HTTP/2 for transport, Protocol Buffers as the interface description language, and provides features such as authentication, bidirectional streaming and flow control, blocking or nonblocking bindings, and cancellation and timeouts. It generates cross- platform client and server bindings for many languages. — wikipedia

The main use case is for machine- to-machine communication. Google is currently migrating all their internal microservices to communicate via gRPC because the protocol provides a uniform interface for defining authentication and load balancing. As well, it also is more efficient communication protocol compared to REST. There is support for mobile and the web, however, these use cases are not as widely used. It supports both request-response and streaming style communication.

### Design Strategy

- Define types and procedures
- Define service methods
- Create service definition
- Incorporate into build

### Comparison to REST


### Key Concepts

### Authentication &amp; Authorization

### Type System

### Tech Stack

### Challenges

- Using Vert.x, Kotlin, gRPC, Gradle was challenging due to lack of experience. The Vert.x documentation appears to be out dated and the Gradle configuration is off. It's recommended to consult the gRPC.io Kotlin examples.
- The Vert.x documentation does not mention that the Java plugin must be used with the Gradle configuration. Without this, the gRPC generated code will fail on the compileJava step of Kotlin.

### Conclusions

### Unanswered Questions

### Resources

- [gRPC.io](https://grpc.io/docs/)
- vert.x gRPC
  - [Docs](https://vertx.io/docs/vertx-grpc/java/)
  - [Examples](https://github.com/vert-x3/vertx-examples/tree/master/grpc-examples)
- [Kotlin gRPC Gradle build file](https://github.com/grpc/grpc-kotlin/blob/master/examples/build.gradle)

