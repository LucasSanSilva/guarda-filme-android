package com.guardafilme.ui.welcome

import com.guardafilme.data.UserRepository
import com.guardafilme.model.WatchedMovie
import javax.inject.Inject

/**
 * Created by lucassantos on 30/10/17.
 */
class WelcomePresenter @Inject constructor(val userRepository: UserRepository): WelcomeContract.Presenter {

    var view: WelcomeContract.View? = null

    override fun attach(view: WelcomeContract.View) {
        this.view = view
    }

    override fun dettach() {
        this.view = null
    }

    override fun load() {
        view?.showLoading()
        userRepository.getWatchedMovies({ watchedMovies ->
            view?.hideLoading()
            if (watchedMovies.isNotEmpty()) {
                view?.hideTooltip()
                view?.addWatchedMovies(watchedMovies)
                view?.showMoviesList()
            } else {
                view?.hideMoviesList()
                view?.showTooltip()
            }
        })
    }

    override fun addMovie() {
        view?.showAddMovie()
    }

    override fun editMovie(watchedMovie: WatchedMovie, updatedDate: Long, updatedRate: Float) {
        val updatedWatchedMovie = watchedMovie.copy(watchedDate = updatedDate, rate = updatedRate)
        userRepository.updateWatchedMovie(updatedWatchedMovie, { success ->
            if (success) load()
        })
    }

    override fun deleteMovie(watchedMovie: WatchedMovie) {
        userRepository.deleteWatchedMovie(watchedMovie.uid, { success ->
            if (success) load()
        })
    }

    override fun logout() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}