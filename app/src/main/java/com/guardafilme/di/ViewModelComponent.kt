package com.guardafilme.di

import dagger.Component
import com.guardafilme.ui.welcome.WelcomeViewModel

/**
 * Created by lucassantos on 20/10/17.
 */
@ViewModelScoped
@Component(modules = arrayOf(ViewModelModule::class))
interface ViewModelComponent {
    fun inject(welcomeViewModel: WelcomeViewModel)
}