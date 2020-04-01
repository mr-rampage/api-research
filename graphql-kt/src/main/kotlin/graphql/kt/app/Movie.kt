package graphql.kt.app

data class Movie(
  val title: String,
  val year: Int = 0,
  val imdbRating: Float = 0f,
  val genres: MutableList<Genre> = mutableListOf()
)
