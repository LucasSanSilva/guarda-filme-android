package com.guardafilme.ui.welcome

import com.guardafilme.di.ActivityScoped
import dagger.Binds
import dagger.Module

/**
 * Created by lucassantos on 21/10/17.
 */
@Module
abstract class WelcomeActivityModule {

    @ActivityScoped
    @Binds
    abstract fun provideWelcomePresenter(welcomePresenter: WelcomePresenter): WelcomeContract.Presenter
}