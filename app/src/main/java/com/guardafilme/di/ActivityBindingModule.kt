package com.guardafilme.di

import com.guardafilme.ui.main.MainActivity
import com.guardafilme.ui.main.MainActivityModule
import com.guardafilme.ui.moviedetail.MovieDetailActivity
import com.guardafilme.ui.moviedetail.MovieDetailActivityModule
import com.guardafilme.ui.searchmovie.SearchMovieActivity
import com.guardafilme.ui.searchmovie.SearchMovieActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.guardafilme.ui.welcome.WelcomeActivity
import com.guardafilme.ui.welcome.WelcomeActivityModule

/**
 * Created by lucassantos on 19/10/17.
 */
@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(WelcomeActivityModule::class))
    abstract fun welcomeActivity(): WelcomeActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    abstract fun mainActivity(): MainActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(SearchMovieActivityModule::class))
    abstract fun searchMovieActivity(): SearchMovieActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(MovieDetailActivityModule::class))
    abstract fun movieDetailActivity(): MovieDetailActivity
}