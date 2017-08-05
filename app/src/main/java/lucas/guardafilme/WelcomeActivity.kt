package lucas.guardafilme

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.firebase.ui.auth.IdpResponse

/**
 * Created by lucassantos on 05/08/17.
 */
class WelcomeActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
    }

    companion object {
        fun createIntent(context: Context, response: IdpResponse?): Intent {
            val intent = Intent(context, WelcomeActivity::class.java)
            return intent
        }
    }

}