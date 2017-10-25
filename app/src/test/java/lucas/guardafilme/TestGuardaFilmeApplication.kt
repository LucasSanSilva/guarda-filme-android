package lucas.guardafilme

import android.app.Activity
import android.support.multidex.MultiDexApplication
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import lucas.guardafilme.di.DaggerTestApplicationComponent
import javax.inject.Inject

/**
 * Created by lucassantos on 24/10/17.
 */
class TestGuardaFilmeApplication: MultiDexApplication(), HasActivityInjector {
    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerTestApplicationComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingActivityInjector
    }
}