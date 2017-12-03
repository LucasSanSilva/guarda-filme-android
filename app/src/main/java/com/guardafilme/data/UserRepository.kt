package com.guardafilme.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.guardafilme.model.Movie
import com.guardafilme.model.User
import com.guardafilme.model.WatchedMovie
import javax.inject.Inject

/**
 * Created by lucassantos on 17/10/17.
 */
interface UserRepository {
    fun getCurrentUser(): User?

    fun getUserId(): String

    fun getWatchedMovies(listener: (List<WatchedMovie>) -> Unit)

    fun deleteWatchedMovie(
            watchedMovieId: String,
            onComplete: (success: Boolean) -> Unit
    )

    fun updateWatchedMovie(
            watchedMovie: WatchedMovie,
            onComplete: (success: Boolean) -> Unit
    )

    fun addWatchedMove(
            movie: Movie,
            watchedDate: Long,
            rate: Float,
            onComplete: (success: Boolean) -> Unit
    )
}