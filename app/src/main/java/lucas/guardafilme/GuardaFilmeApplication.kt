package lucas.guardafilme

import android.support.annotation.VisibleForTesting
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import lucas.guardafilme.data.UserRepository
import lucas.guardafilme.di.DaggerApplicationComponent
import javax.inject.Inject

/**
 * Created by lucassantos on 19/10/17.
 */
class GuardaFilmeApplication: DaggerApplication() {
//    @VisibleForTesting
//    @Inject
//    lateinit var userRepository: UserRepository

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerApplicationComponent.builder()
                .application(this)
                .build()
        appComponent.inject(this)
        return appComponent
    }

}