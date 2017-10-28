package com.guardafilme.data

import android.arch.lifecycle.LiveData
import com.guardafilme.model.User

/**
 * Created by lucassantos on 21/10/17.
 */
interface AuthRepository {
    fun logoutUser()
    fun loginUser()
    fun getCurrentUser(): LiveData<User>
}