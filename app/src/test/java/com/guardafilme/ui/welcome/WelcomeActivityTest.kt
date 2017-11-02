package com.guardafilme.ui.welcome

import android.support.design.widget.FloatingActionButton
import com.guardafilme.R
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowApplication
import android.content.Intent
import android.view.View
import com.guardafilme.BuildConfig
import com.guardafilme.MockGuardaFilmeApplication
import com.guardafilme.ui.searchmovie.SearchMovieActivity
import org.junit.Assert.assertEquals
import org.robolectric.annotation.Config


/**
 * Created by lucassantos on 01/11/17.
 */
@RunWith(RobolectricTestRunner::class)
@Config(
        constants = BuildConfig::class,
        application = MockGuardaFilmeApplication::class,
        packageName = "com.guardafilme"
)
class WelcomeActivityTest {

    @Test
    fun clickingAdd_shouldStartSearchMovieActivity() {
        val activity = Robolectric.setupActivity(WelcomeActivity::class.java)
        activity.findViewById<View>(R.id.fab).performClick()

        val expectedIntent = Intent(activity, SearchMovieActivity::class.java)
        val actual = ShadowApplication.getInstance().nextStartedActivity
        assertEquals(expectedIntent.component, actual.component)
    }

}