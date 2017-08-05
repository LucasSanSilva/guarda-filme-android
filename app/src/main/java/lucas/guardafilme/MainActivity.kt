package lucas.guardafilme

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.firebase.ui.auth.AuthUI
import java.util.*
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.ResultCodes
import com.firebase.ui.auth.IdpResponse

class MainActivity : AppCompatActivity() {

    val RC_SIGN_IN = 1234

    var mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()

        if (mAuth.currentUser != null) {
            // already signed in
        } else {
            // not signed in
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(
                                    Arrays.asList(AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                            AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build(),
                                            AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                            .build(),
                    RC_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            // Successfully signed in
            if (resultCode == ResultCodes.OK) {
                startActivity(WelcomeActivity.createIntent(this, response))
                finish()
                return
            } else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    showSnackbar(R.string.sign_in_cancelled)
                    return
                }

                if (response.errorCode == ErrorCodes.NO_NETWORK) {
                    showSnackbar(R.string.no_internet_connection)
                    return
                }

                if (response.errorCode == ErrorCodes.UNKNOWN_ERROR) {
                    showSnackbar(R.string.unknown_error)
                    return
                }
            }

            showSnackbar(R.string.unknown_sign_in_response)
        }
    }

    fun showSnackbar(messageId: Int) {
        val snack = Snackbar.make(findViewById(R.id.main_layout), messageId, Snackbar.LENGTH_LONG)
        snack.show()
    }
}
