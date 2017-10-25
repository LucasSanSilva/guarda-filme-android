package lucas.guardafilme.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import lucas.guardafilme.ui.welcome.TestWelcomeActivityModule
import lucas.guardafilme.ui.welcome.WelcomeActivity

/**
 * Created by lucassantos on 24/10/17.
 */
@Module
abstract class TestActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(TestWelcomeActivityModule::class))
    abstract fun welcomeActivity(): WelcomeActivity
}