package com.guardafilme.data

import android.arch.lifecycle.LiveData
import com.guardafilme.model.MoviesList

/**
 * Created by lucassantos on 21/10/17.
 */
interface MoviesRepository {
    fun searchMovies(searchQuery: String): LiveData<MoviesList>
}