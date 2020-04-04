package grpc.kt.app

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.Promise

class MainVerticle : AbstractVerticle() {

  override fun start(startPromise: Promise<Void>) {
    super.start(startPromise)
    vertx
      .createHttpServer()
      .requestHandler { req ->
        req.response()
          .putHeader("content-type", "text/plain")
          .end("Hello from Vert.x!")
      }
      .listen(8888) { http ->
        if (http.succeeded()) {
          startPromise.complete()
          println("HTTP server started on port 8888")
        } else {
          startPromise.fail(http.cause())
        }
      }
  }
}
