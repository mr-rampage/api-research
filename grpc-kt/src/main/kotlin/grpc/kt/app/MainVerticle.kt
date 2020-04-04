package grpc.kt.app

import io.vertx.core.AbstractVerticle

class MainVerticle: AbstractVerticle() {
  override fun start() {
    vertx.deployVerticle(HelloServer()) {
      if (it.succeeded()) {
        vertx.deployVerticle(HelloClient()) {
          if (it.succeeded()) {
            println("Hello Server & Client deployed")
          } else {
            println("Hello Client failed to deploy")
          }
        }
      } else {
        println("Hello Server failed to deploy")
      }
    }

  }
}
