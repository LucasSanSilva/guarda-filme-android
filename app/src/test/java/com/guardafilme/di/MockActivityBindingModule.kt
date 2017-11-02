package com.guardafilme.di

import com.guardafilme.ui.welcome.MockWelcomeModule
import com.guardafilme.ui.welcome.WelcomeActivity
import com.guardafilme.ui.welcome.WelcomeActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by lucassantos on 01/11/17.
 */
@Module
abstract class MockActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(MockWelcomeModule::class))
    abstract fun welcomeActivity(): WelcomeActivity
}