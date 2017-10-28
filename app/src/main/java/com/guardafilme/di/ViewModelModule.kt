package com.guardafilme.di

import dagger.Module
import com.guardafilme.data.UserRepository

/**
 * Created by lucassantos on 20/10/17.
 */
@Module
abstract class ViewModelModule {
    abstract fun bindUserRepository(): UserRepository
}