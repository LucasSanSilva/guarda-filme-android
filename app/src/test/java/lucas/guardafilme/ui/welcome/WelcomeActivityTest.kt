package lucas.guardafilme.ui.welcome

import android.content.Intent
import android.support.design.widget.FloatingActionButton
import kotlinx.android.synthetic.main.activity_welcome.view.*
import lucas.guardafilme.BuildConfig
import lucas.guardafilme.R
import lucas.guardafilme.TestGuardaFilmeApplication
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Created by lucassantos on 19/10/17.
 */
@Config(constants = BuildConfig::class, application = TestGuardaFilmeApplication::class)
@RunWith(RobolectricTestRunner::class)
class WelcomeActivityTest {

    @Test
    fun clickAdd_shouldStartSearchMovieActivity() {
        val intent = Intent()
        intent.putExtra(WelcomeActivity.USER_ID_EXTRA, "teste")
        val activity: WelcomeActivity = Robolectric
                .buildActivity(WelcomeActivity::class.java, intent)
                .create()
                .get()
        activity.findViewById<FloatingActionButton>(R.id.fab).performClick()
    }

}