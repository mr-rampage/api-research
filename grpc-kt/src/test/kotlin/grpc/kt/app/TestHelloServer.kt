package grpc.kt.app

import io.vertx.core.Vertx
import io.vertx.junit5.VertxExtension
import io.vertx.junit5.VertxTestContext
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(VertxExtension::class)
class TestHelloServer {

  @BeforeEach
  fun deploy_verticle(vertx: Vertx, testContext: VertxTestContext) {
    vertx.deployVerticle(HelloServer(), testContext.succeeding { testContext.completeNow() })
  }

  @Test
  fun verticle_deployed(testContext: VertxTestContext) {
    testContext.completeNow()
  }
}
