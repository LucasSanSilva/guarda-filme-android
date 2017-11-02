package com.guardafilme.di

import android.app.Application
import com.guardafilme.MockGuardaFilmeApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by lucassantos on 01/11/17.
 */
@Singleton
@Component(modules = arrayOf(
        MockApplicationModule::class,
        AndroidInjectionModule::class,
        MockActivityBindingModule::class))
interface MockApplicationComponent {

    fun inject(application: MockGuardaFilmeApplication)

//    fun getUserRepository(): UserRepository

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): MockApplicationComponent.Builder

        fun build(): MockApplicationComponent

    }

}