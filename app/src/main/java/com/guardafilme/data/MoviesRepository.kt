package com.guardafilme.data

import android.arch.lifecycle.LiveData
import com.guardafilme.model.Movie
import com.guardafilme.model.MoviesList
import info.movito.themoviedbapi.model.MovieDb

/**
 * Created by lucassantos on 21/10/17.
 */
interface MoviesRepository {
    fun searchMovies(apiKey: String, query: String, onMoviesLoaded: (movies: List<Movie>) -> Unit)

    fun getMovieDetails(apiKey: String, movieId: Int, onMovieLoaded: (movie: MovieDb) -> Unit)
}