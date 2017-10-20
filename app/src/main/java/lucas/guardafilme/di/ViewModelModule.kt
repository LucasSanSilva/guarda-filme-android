package lucas.guardafilme.di

import dagger.Module
import lucas.guardafilme.data.UserRepository

/**
 * Created by lucassantos on 20/10/17.
 */
@Module
abstract class ViewModelModule {
    abstract fun bindUserRepository(): UserRepository
}