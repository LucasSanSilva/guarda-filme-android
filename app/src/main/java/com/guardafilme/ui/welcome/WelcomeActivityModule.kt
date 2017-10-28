package com.guardafilme.ui.welcome

import dagger.Binds
import dagger.Module
import dagger.Provides
import com.guardafilme.data.AuthProvider
import com.guardafilme.data.GFAuthProvider
import com.guardafilme.di.ActivityScoped

/**
 * Created by lucassantos on 21/10/17.
 */
@Module
class WelcomeActivityModule {

    @ActivityScoped
    @Provides
    fun provideAuthProvider(): AuthProvider {
        return GFAuthProvider()
    }
}