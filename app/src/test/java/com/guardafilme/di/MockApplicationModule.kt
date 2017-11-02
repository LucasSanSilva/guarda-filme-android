package com.guardafilme.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by lucassantos on 01/11/17.
 */
@Module
class MockApplicationModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }
}