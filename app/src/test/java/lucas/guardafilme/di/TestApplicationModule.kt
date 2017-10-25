package lucas.guardafilme.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by lucassantos on 25/10/17.
 */
@Module
class TestApplicationModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }
}