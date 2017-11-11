package com.guardafilme.data

import android.content.Intent
import android.support.v4.app.FragmentActivity
import com.firebase.ui.auth.AuthUI
import com.guardafilme.R
import java.util.*
import javax.inject.Inject

/**
 * Created by lucassantos on 01/11/17.
 */
class AuthProvider @Inject constructor() {
    fun getAuthIntent(): Intent {
        return AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(
                        Arrays.asList(AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build(),
                                AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                .setLogo(R.drawable.logo)
                .setTheme(R.style.LoginTheme)
                .build()
    }

    fun logoutUser(activity: FragmentActivity, completionListener: () -> Unit) {
        AuthUI.getInstance()
                .signOut(activity)
                .addOnCompleteListener { task -> completionListener() }
    }
}