package com.guardafilme.di

import com.guardafilme.ui.main.MainActivity
import com.guardafilme.ui.main.MainActivityModule
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
}