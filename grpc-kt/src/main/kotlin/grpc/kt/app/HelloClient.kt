package grpc.kt.app

import io.vertx.core.AbstractVerticle
import io.vertx.core.AsyncResult
import io.vertx.grpc.VertxChannelBuilder


class HelloClient : AbstractVerticle() {
  override fun start() {
    val channel = VertxChannelBuilder
      .forAddress(vertx, "localhost", 50051)
      .usePlaintext()
      .build()

    val stub = GreeterGrpc.newVertxStub(channel)

    val request = HelloRequest.newBuilder().setName("Intelliware").build()

    stub.sayHello(request) {
      if (it.succeeded()) {
        println("Succeeded ${it.result().message}")
      } else {
        it.cause().printStackTrace()
      }
    }
  }
}
