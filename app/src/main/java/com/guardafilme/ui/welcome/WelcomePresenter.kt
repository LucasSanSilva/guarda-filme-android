package com.guardafilme.ui.welcome

import com.guardafilme.data.UserRepository
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
        userRepository.getWatchedMovies({ watchedMovies ->
            view?.addWatchedMovies(watchedMovies)
        })
    }

    override fun addMovie() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun editMovie() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteMovie() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun logout() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}