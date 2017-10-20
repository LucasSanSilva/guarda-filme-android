package lucas.guardafilme.di

import dagger.Component
import lucas.guardafilme.ui.welcome.WelcomeViewModel

/**
 * Created by lucassantos on 20/10/17.
 */
@ViewModelScoped
@Component(modules = arrayOf(ViewModelModule::class))
interface ViewModelComponent {
    fun inject(welcomeViewModel: WelcomeViewModel)
}