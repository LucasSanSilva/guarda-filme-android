package com.guardafilme

import android.app.Activity
import android.support.multidex.MultiDexApplication
import com.guardafilme.di.DaggerMockApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by lucassantos on 01/11/17.
 */
class MockGuardaFilmeApplication: MultiDexApplication(), HasActivityInjector {
    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerMockApplicationComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingActivityInjector
    }
}