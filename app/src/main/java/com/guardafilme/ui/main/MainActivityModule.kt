package com.guardafilme.ui.main

import com.guardafilme.di.ActivityScoped
import dagger.Binds
import dagger.Module

/**
 * Created by lucassantos on 11/11/17.
 */
@Module
abstract class MainActivityModule {
    @ActivityScoped
    @Binds
    abstract fun provideMainPresenter(mainPresenter: MainPresenter): MainContract.Presenter
}