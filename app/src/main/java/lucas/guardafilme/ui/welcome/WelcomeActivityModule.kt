package lucas.guardafilme.ui.welcome

import dagger.Binds
import lucas.guardafilme.data.AuthProvider
import lucas.guardafilme.data.GFAuthProvider
import lucas.guardafilme.di.ActivityScoped

/**
 * Created by lucassantos on 21/10/17.
 */
abstract class WelcomeActivityModule {
    @ActivityScoped
    @Binds abstract fun authProvider(authProvider: GFAuthProvider): AuthProvider
}