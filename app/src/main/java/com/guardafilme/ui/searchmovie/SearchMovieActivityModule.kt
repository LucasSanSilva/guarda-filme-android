package com.guardafilme.ui.searchmovie

import com.guardafilme.di.ActivityScoped
import dagger.Binds
import dagger.Module

/**
 * Created by lucassantos on 17/12/17.
 */
@Module
abstract class SearchMovieActivityModule {

    @ActivityScoped
    @Binds
    abstract fun provideSearchMoviePresenter(searchMoviePresenter: SearchMoviePresenter): SearchMovieContract.Presenter
}