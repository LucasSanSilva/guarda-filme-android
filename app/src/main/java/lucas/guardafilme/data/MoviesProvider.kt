package lucas.guardafilme.data

import android.app.Activity
import android.content.Context
import info.movito.themoviedbapi.TmdbApi
import info.movito.themoviedbapi.model.MovieDb
import lucas.guardafilme.R
import lucas.guardafilme.Utils
import lucas.guardafilme.model.Movie
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by lucassantos on 12/10/17.
 */
class MoviesProvider {
    companion object {
        fun searchMovies(context: Context, query: String, onMoviesLoaded: (movies: List<Movie>) -> Unit) {
            doAsync {
                val searchResult = TmdbApi(context.getString(R.string.tmdb_key)).search.searchMovie(query, 0, "pt-BR", true, 0)
                uiThread {
                    if (!(context as Activity).isDestroyed) {
                        onMoviesLoaded(convertTmdbMoviesToInternal(searchResult.results))
                    }
                }
            }
        }

        private fun tmdbMovieToInternalMovie(tmdbMovie: MovieDb): Movie {
            val movie = Movie()
            movie.id = tmdbMovie.id
            movie.title = tmdbMovie.title
            movie.originalTitle = tmdbMovie.originalTitle
            movie.year = Utils.getYearFromMovieReleaseDate(tmdbMovie.releaseDate)
            movie.poster = tmdbMovie.posterPath ?: ""

            return movie
        }

        private fun convertTmdbMoviesToInternal(tmdbMoviesList: List<MovieDb>): List<Movie> {
            return tmdbMoviesList.map { tmdbMovie -> tmdbMovieToInternalMovie(tmdbMovie) }
        }
    }
}