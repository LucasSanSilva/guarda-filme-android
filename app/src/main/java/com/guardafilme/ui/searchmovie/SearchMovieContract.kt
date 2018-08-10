package com.guardafilme.ui.searchmovie

import com.guardafilme.base.BasePresenter
import com.guardafilme.base.BaseView
import com.guardafilme.model.Movie

/**
 * Created by lucassantos on 20/11/17.
 */
interface SearchMovieContract {

    interface View: BaseView {
        fun addMovies(movies: List<Movie>?)
        fun showMoviesList()
        fun hideMoviesList()
        fun finishWithSuccess()
    }

    interface Presenter: BasePresenter<View> {
        fun searchMovies(apiKey: String, query: String)
        fun movieClicked(movie: Movie, watchedDate: Long, rate: Float)
    }

}