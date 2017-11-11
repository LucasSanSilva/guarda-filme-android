package com.guardafilme.ui.main

import com.guardafilme.base.BasePresenter
import com.guardafilme.model.User

/**
 * Created by lucassantos on 06/11/17.
 */
interface MainContract {

    interface View {
        fun showLoginMessage()
        fun openLogin()
        fun openWelcome()
        fun showCanceledLoginMessage()
        fun showNetworkErrorMessage()
        fun showUnknownErrorMessage()
    }

    interface Presenter: BasePresenter<View> {
        fun loadCurrentUser()
        fun loginClicked()
        fun loginError(errorCode: Int?)
    }

}