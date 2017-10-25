package lucas.guardafilme.ui.welcome

import dagger.Binds
import dagger.Module
import dagger.Provides
import lucas.guardafilme.data.AuthProvider
import lucas.guardafilme.di.ActivityScoped
import org.mockito.Mockito

/**
 * Created by lucassantos on 24/10/17.
 */
@Module
class TestWelcomeActivityModule {

    @ActivityScoped
    @Provides
    fun authProvider(): AuthProvider {
        return Mockito.mock(AuthProvider::class.java)
    }
}