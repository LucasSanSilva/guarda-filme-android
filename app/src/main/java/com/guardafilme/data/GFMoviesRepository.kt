package com.guardafilme.data

import com.guardafilme.Utils
import com.guardafilme.model.Movie
import info.movito.themoviedbapi.TmdbApi
import info.movito.themoviedbapi.model.MovieDb
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import javax.inject.Inject

/**
 * Created by lucassantos on 20/11/17.
 */
class GFMoviesRepository @Inject constructor(): MoviesRepository {
    override fun searchMovies(apiKey: String, query: String, onMoviesLoaded: (movies: List<Movie>) -> Unit) {
        doAsync {
            val searchResult = TmdbApi(apiKey).search.searchMovie(query, 0, "pt-BR", true, 0)
            uiThread {
                onMoviesLoaded(convertTmdbMoviesToInternal(searchResult.results))
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
        movie.backdrop = tmdbMovie.backdropPath ?: ""

        return movie
    }

    private fun convertTmdbMoviesToInternal(tmdbMoviesList: List<MovieDb>): List<Movie> {
        return tmdbMoviesList.map { tmdbMovie -> tmdbMovieToInternalMovie(tmdbMovie) }
    }
}