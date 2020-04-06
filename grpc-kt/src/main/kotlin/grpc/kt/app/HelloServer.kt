package grpc.kt.app

import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import io.vertx.grpc.VertxServerBuilder

class HelloServer : AbstractVerticle() {

  override fun start() {
    val service = object: GreeterGrpc.GreeterVertxImplBase() {
      override fun sayHello(request: HelloRequest?, response: Promise<HelloReply>?) {
        println("Hello ${request?.name}")
        response?.complete(HelloReply.newBuilder().setMessage("Hello ${request?.name}, from Kotlin").build())
      }
    }

    val server = VertxServerBuilder
      .forAddress(vertx, "localhost", 8080)
      .addService(service)
      .build()

    server.start {
      if (it.succeeded()) {
        println("gRPC service started")
      } else {
        println("Could not start server ${it.cause().message}")
      }
    }
  }
}
