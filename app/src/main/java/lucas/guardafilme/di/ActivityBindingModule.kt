package lucas.guardafilme.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import lucas.guardafilme.ui.welcome.WelcomeActivity
import lucas.guardafilme.ui.welcome.WelcomeActivityModule

/**
 * Created by lucassantos on 19/10/17.
 */
@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(WelcomeActivityModule::class))
    abstract fun welcomeActivity(): WelcomeActivity
}