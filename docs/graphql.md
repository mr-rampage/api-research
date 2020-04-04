# GraphQL

## Summary

Created by Facebook, this is a query based API. It is agnostic of transfer protocol, but is seems to be mainly used over HTTP. The key concept is that a client can interact with data through a declarative manner. By defining GraphQL payloads, the GraphQL server will respond by returning data or mutating data. GraphQL also supports a reactive interface through subscriptions. GraphQL relies heavily on types and has a fairly strong type system.

## Design Strategy

- Define your types, queries, and mutations
- Implement resolvers to handle these types &amp; fields - i.e. get the data from the data source
- Repeat as new requirements arrive

## Comparisons to REST

- Solves the over/under fetching data problem
- Only a single URL
- Introspection - clients can ask the server for the schema (i.e. OpenAPI, but built in)

## Key Concepts

- Queries
  - Supports destructuring of fragments to reduce boilerplate
- Mutations
- Subscriptions
- Resolver
  - Batch Resolving
  - Queries are resolved by walking the graph
  - One pattern of authorization happens here

## Authentication &amp; Authorization

Authentication is handled with standard web technologies (i.e. JWT, OAuth, Cookies, etc...). Starting the Apollo server, authentication can then be handled by the context property of Apollo. Authorization can be handled in the resolver by delegating to a business logic layer or module. Another pattern is to use high- order functions to construct the resolvers and inject the authorization logic within each resolver. See resources for examples.

## Type System

- Scalars: String, Int, Float, Boolean, ID
- Object
- Enum
- Interfaces
- Union Types (i.e. union Person = Adult | Child) - conditional fragments used to query
- Custom Types (i.e. Date)

## Tech Stack

### JavaScript
- [GRANDstack](https://grandstack.io) - starter with GraphQL, React, Apollo, and Neo4j
- [GraphQL Yoga](https://github.com/prisma-labs/graphql-yoga) - kind of like &quot;spring boot&quot; for graphql and node.js. Built on express.js &amp; apollo-server

### Kotlin
- [ExpediaGroup](https://github.com/ExpediaGroup/graphql-kotlin) - starter with GraphQL, Kotlin, Spring Boot
- [Vert.x](https://vertx.io) - starter with GraphQL support under Technical Review

### Java

- [GraphQL Spring Kickstart](https://github.com/graphql-java-kickstart/graphql-spring-boot) - starter with GraphQL, Java, Spring Boot

## Challenges

- No caching since queries could be anything
- Vulnerable to DDOS attacks - although there are documented best practices to avoid DDOS

## Conclusions

The Javascript side seems more suited for GraphQL as the Apollo server automates much of the work involved in creating a GraphQL server using the GRANDstack. On the Kotlin/Java side, there is a bit more work involved in wiring up the GraphQL schema to the appropriate mutations, queries, and subscriptions. Compared to Vert.x's OpenAPI support, the GraphQL is lacking in terms of the automated code generation (i.e. wiring and models). However, this seems to support the use case of using GraphQL of a backend for frontend. If one was to use GraphQL to support a single page application, it would make sense for this to be written in Node.js.

A possible recommendation is to start a project using GraphQL on Node.js to get develop up and running quickly. The UI and GraphQL server could evolve independently and data could be gathered to understand how clients use the data. Afterwards, when there is an understanding of how the data is used, a REST API could be crafted to reflect these use cases and the Apollo client/server could be deprecated and replaced with a REST client/server.

The introspection feature of GraphQL makes it really easy to work with and to understand what data/queries are available.

## Unanswered Questions

- How are migration scripts managed for Neo4j? (Not relevant because Neo4j could be replaced with PostgreSQL)
- Why does GraphQL &amp; Spring Boot require [so much code](https://github.com/expediagroup/graphql-kotlin/tree/master/examples/spring/src/main/kotlin/com/expediagroup/graphql/examples)?
- Why doesn't GraphQL in Java/Kotlin don't seem to understand the "@relation" directive?
- How does GraphQL perform on a relational database? GraphQL operates by "walking" the graph when resolving a query. This makes it optimal for graph databases.

## Resources

- [How to GraphQL](https://www.howtographql.com/basics/0-introduction/)
- [Authorization in GraphQL](https://blog.apollographql.com/authorization-in-graphql-452b1c402a9)
- [Spring GraphQL Tutorial](https://www.baeldung.com/spring-graphql)
- [Tutorials for most languages](https://www.howtographql.com/choose/)

