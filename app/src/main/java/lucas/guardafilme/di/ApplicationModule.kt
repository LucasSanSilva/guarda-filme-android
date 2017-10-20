package lucas.guardafilme.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

/**
 * Created by lucassantos on 19/10/17.
 */
@Module
abstract class ApplicationModule {
    @Binds
    abstract fun bindContext(application: Application): Context
}