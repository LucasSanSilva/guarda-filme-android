package com.guardafilme.ui.main

import com.firebase.ui.auth.ErrorCodes
import com.guardafilme.data.UserRepository
import com.guardafilme.model.User
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.*

/**
 * Created by lucassantos on 07/11/17.
 */
class MainPresenterTest {

    @Mock
    lateinit var userRepository: UserRepository

    @Mock
    lateinit var mainView: MainContract.View

    lateinit var mainPresenter: MainContract.Presenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        mainPresenter = MainPresenter(userRepository)
        mainPresenter.attach(mainView)
    }

    @Test
    fun loadCurrentUser_shouldOpenWelcomeViewWhenUserIsLoggedIn() {
        Mockito.`when`(userRepository.getCurrentUser()).thenReturn(User("1234", "Teste"))

        mainPresenter.loadCurrentUser()

        verify(mainView).openWelcome()
    }

    @Test
    fun loadCurrentUser_shouldOpenLoginWhenCurrentUserIsNull() {
        mainPresenter.loadCurrentUser()

        verify(mainView).showLoginMessage()
    }

    @Test
    fun loginError_shouldShowCanceledLoginMessage() {
        mainPresenter.loginError(null)

        verify(mainView).showCanceledLoginMessage()
    }

    @Test
    fun loginError_shouldShowNetworkErrorMessage() {
        mainPresenter.loginError(ErrorCodes.NO_NETWORK)

        verify(mainView).showNetworkErrorMessage()
    }

    @Test
    fun loginError_shouldShowUnknownErrorMessage() {
        mainPresenter.loginError(ErrorCodes.UNKNOWN_ERROR)

        verify(mainView).showUnknownErrorMessage()
    }

    @Test
    fun loginError_shouldShowUnknownErrorMessage_whenInvalidCode() {
        mainPresenter.loginError(72)

        verify(mainView).showUnknownErrorMessage()
    }

}