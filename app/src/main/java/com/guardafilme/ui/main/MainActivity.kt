package com.guardafilme.ui.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.firebase.ui.auth.IdpResponse
import com.firebase.ui.auth.ResultCodes
import com.google.android.gms.ads.MobileAds
import com.guardafilme.R
import com.guardafilme.data.AuthProvider
import com.guardafilme.ui.welcome.WelcomeActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    val RC_SIGN_IN = 1234

    @Inject
    lateinit var presenter: MainContract.Presenter

    @Inject
    lateinit var authProvider: AuthProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        presenter.attach(this)

        setContentView(R.layout.activity_main)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorAccentDark)

        MobileAds.initialize(this, getString(R.string.admob_id))
    }

    override fun onStart() {
        super.onStart()

        presenter.loadCurrentUser()

        login_button.setOnClickListener {
            presenter.loginClicked()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            // Successfully signed in
            if (resultCode == ResultCodes.OK) {
                presenter.loadCurrentUser()
            } else {
                // Sign in failed
                presenter.loginError(response?.errorCode)
            }
        }
    }

    override fun showLoginMessage() {
        login_message_layout.visibility = View.VISIBLE
    }

    override fun openLogin() {
        startActivityForResult(
                authProvider.getAuthIntent(),
                RC_SIGN_IN)
    }

    override fun openWelcome() {
        startActivity(WelcomeActivity.createIntent(this))
        finish()
    }

    override fun showCanceledLoginMessage() {
        showSnackbar(R.string.sign_in_cancelled)
    }

    override fun showNetworkErrorMessage() {
        showSnackbar(R.string.no_internet_connection)
    }

    override fun showUnknownErrorMessage() {
        showSnackbar(R.string.unknown_error)
    }

    fun showSnackbar(messageId: Int) {
        val snack = Snackbar.make(findViewById(R.id.main_layout), messageId, Snackbar.LENGTH_LONG)
        snack.show()
    }
}
