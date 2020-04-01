package graphql.kt.app

import graphql.GraphQL
import graphql.schema.DataFetchingEnvironment
import graphql.schema.idl.RuntimeWiring.newRuntimeWiring
import graphql.schema.idl.SchemaGenerator
import graphql.schema.idl.SchemaParser
import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.graphql.GraphQLHandler
import io.vertx.ext.web.handler.graphql.VertxDataFetcher


class MainVerticle : AbstractVerticle() {
  val movies = listOf(Movie(title = "Jaws"), Movie(title = "Baby Shark"))
  val genres = listOf(Genre(name = "Thriller"), Genre(name = "Family"))

  override fun start(startPromise: Promise<Void>?) {
    super.start(startPromise)

    linkMoviesGenres()

    val router = Router.router(vertx)
    router.route("/graphql").handler(GraphQLHandler.create(createGraphQL()))

    vertx.createHttpServer()
      .requestHandler(router)
      .listen(8080)
  }

  private fun linkMoviesGenres() {
    movies[0].genres.add(genres[0])
    genres[0].movies.add(movies[0])

    movies[1].genres.add(genres[1])
    genres[1].movies.add(movies[1])
  }

  private fun createGraphQL(): GraphQL? {
    val schema = vertx.fileSystem().readFileBlocking("schema.graphql").toString()
    val schemaParser = SchemaParser()
    val typeDefinitionRegistry = schemaParser.parse(schema)

    val runtimeWiring = newRuntimeWiring()
      .type("Query") { builder ->
        // Defining resolvers... :(
          builder
            .dataFetcher("allMovies", VertxDataFetcher(this::getAllMovies))
            .dataFetcher("allGenres", VertxDataFetcher(this::getAllGenres))
      }
      .build()

    val schemaGenerator = SchemaGenerator()
    val graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring)
    return GraphQL.newGraphQL(graphQLSchema).build()
  }

  private fun getAllMovies(env: DataFetchingEnvironment, future: Promise<List<Movie>>) {
    future.complete(movies)
  }

  private fun getAllGenres(env: DataFetchingEnvironment, future: Promise<List<Genre>>) {
    future.complete(genres)
  }
}
