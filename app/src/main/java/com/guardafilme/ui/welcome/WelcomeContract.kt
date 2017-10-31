package com.guardafilme.ui.welcome

import com.guardafilme.base.BasePresenter
import com.guardafilme.model.WatchedMovie

/**
 * Created by lucassantos on 30/10/17.
 */
interface WelcomeContract {

    interface View {
        fun addWatchedMovies(watchedMovies: List<WatchedMovie>)
        fun showLoading()
        fun hideLoading()
        fun showMoviesList()
        fun hideMoviesList()
        fun showTooltip()
        fun hideTooltip()
    }

    interface Presenter: BasePresenter<View> {
        fun load()
        fun addMovie()
        fun editMovie()
        fun deleteMovie()
        fun logout()
    }
}