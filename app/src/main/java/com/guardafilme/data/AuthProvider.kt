package com.guardafilme.data

import android.support.v4.app.FragmentActivity
import com.firebase.ui.auth.AuthUI
import javax.inject.Inject

/**
 * Created by lucassantos on 01/11/17.
 */
class AuthProvider @Inject constructor() {
    fun logoutUser(activity: FragmentActivity, completionListener: () -> Unit) {
        AuthUI.getInstance()
                .signOut(activity)
                .addOnCompleteListener { task -> completionListener() }
    }
}