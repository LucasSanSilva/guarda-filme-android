package com.guardafilme.di

import android.app.Application
import android.content.Context
import com.guardafilme.data.GFUserRepository
import com.guardafilme.data.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by lucassantos on 19/10/17.
 */
@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideUserRepository(userRepository: GFUserRepository): UserRepository {
        return userRepository
    }
}