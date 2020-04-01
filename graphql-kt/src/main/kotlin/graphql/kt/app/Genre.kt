package graphql.kt.app

data class Genre(val name: String, val movies: MutableList<Movie> = mutableListOf())
