package lucas.guardafilme.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import lucas.guardafilme.TestGuardaFilmeApplication
import lucas.guardafilme.ui.welcome.TestWelcomeActivityModule
import javax.inject.Singleton

/**
 * Created by lucassantos on 24/10/17.
 */
@Singleton
@Component(modules = arrayOf(
        TestApplicationModule::class,
        AndroidInjectionModule::class,
        TestActivityBindingModule::class))
interface TestApplicationComponent {

    fun inject(application: TestGuardaFilmeApplication)

//    fun getUserRepository(): UserRepository

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): TestApplicationComponent.Builder

        fun build(): TestApplicationComponent

    }

}