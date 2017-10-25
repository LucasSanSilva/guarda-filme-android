package lucas.guardafilme

import android.app.Activity
import android.support.multidex.MultiDexApplication
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import lucas.guardafilme.di.DaggerApplicationComponent
import javax.inject.Inject


/**
 * Created by lucassantos on 19/10/17.
 */
class GuardaFilmeApplication: MultiDexApplication(), HasActivityInjector {
    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingActivityInjector
    }
}