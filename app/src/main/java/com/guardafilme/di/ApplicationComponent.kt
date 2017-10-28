package com.guardafilme.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import com.guardafilme.GuardaFilmeApplication
import com.guardafilme.data.UserRepository
import com.guardafilme.ui.welcome.WelcomeActivityModule
import javax.inject.Singleton

/**
 * Created by lucassantos on 19/10/17.
 */
@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class,
        AndroidInjectionModule::class,
        ActivityBindingModule::class))
interface ApplicationComponent {

    fun inject(application: GuardaFilmeApplication)

//    fun getUserRepository(): UserRepository

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): ApplicationComponent.Builder

        fun build(): ApplicationComponent

    }

}