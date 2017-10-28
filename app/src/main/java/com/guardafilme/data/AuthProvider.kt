package com.guardafilme.data

import android.support.v4.app.FragmentActivity

/**
 * Created by lucassantos on 21/10/17.
 */
interface AuthProvider {
    fun loginUser(activity: FragmentActivity)

    fun logoutUser(activity: FragmentActivity, completionListener: () -> Unit)
}