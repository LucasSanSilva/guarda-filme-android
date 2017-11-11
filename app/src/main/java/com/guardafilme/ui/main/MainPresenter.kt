package com.guardafilme.ui.main

import com.firebase.ui.auth.ErrorCodes
import com.guardafilme.data.UserRepository
import javax.inject.Inject

/**
 * Created by lucassantos on 07/11/17.
 */
class MainPresenter @Inject constructor(val userRepository: UserRepository): MainContract.Presenter {

    var view: MainContract.View? = null

    override fun attach(view: MainContract.View) {
        this.view = view
    }

    override fun dettach() {
        this.view = null
    }

    override fun loadCurrentUser() {
        val currentUser = userRepository.getCurrentUser()
        if (currentUser != null) {
            view?.openWelcome()
        } else {
            view?.showLoginMessage()
        }
    }

    override fun loginClicked() {
        view?.openLogin()
    }

    override fun loginError(errorCode: Int?) {
        when (errorCode) {
            null -> view?.showCanceledLoginMessage()
            ErrorCodes.NO_NETWORK -> view?.showNetworkErrorMessage()
            ErrorCodes.UNKNOWN_ERROR -> view?.showUnknownErrorMessage()
            else -> view?.showUnknownErrorMessage()
        }
    }
}