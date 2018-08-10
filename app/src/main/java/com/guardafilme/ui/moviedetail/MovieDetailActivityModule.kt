package com.guardafilme.ui.moviedetail

import com.guardafilme.di.ActivityScoped
import dagger.Binds
import dagger.Module

@Module
abstract class MovieDetailActivityModule {

    @ActivityScoped
    @Binds
    abstract fun provideMovieDetailPresenter(movieDetailPresenter: MovieDetailPresenter): MovieDetailContract.Presenter
}