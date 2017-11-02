package com.guardafilme.ui.welcome

import com.guardafilme.di.ActivityScoped
import dagger.Module
import dagger.Provides
import org.mockito.Mock
import org.mockito.Mockito

/**
 * Created by lucassantos on 02/11/17.
 */
@Module
class MockWelcomeModule {

    @ActivityScoped
    @Provides
    fun provideWelcomePresenter(): WelcomeContract.Presenter {
        return Mockito.mock(WelcomeContract.Presenter::class.java)
    }
}