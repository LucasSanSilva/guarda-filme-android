package com.guardafilme.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.guardafilme.model.WatchedMovie
import javax.inject.Inject

/**
 * Created by lucassantos on 17/10/17.
 */
class UserRepository @Inject constructor() {
    fun getUserId(): String {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            return currentUser.uid
        }
        return ""
    }

    fun getWatchedMovies(listener: (List<WatchedMovie>) -> Unit) {
        val databaseRef = FirebaseDatabase
                .getInstance()
                .reference
                .child("watched_movies")
                .child(getUserId())
                .orderByChild("watchedDate")
        databaseRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                val watchedMovies = mutableListOf<WatchedMovie>()

                dataSnapshot?.children?.forEach { childData ->
                    val watchedMovie = childData.getValue(WatchedMovie::class.java)
                    if (watchedMovie != null) {
                        watchedMovies.add(watchedMovie)
                    }
                }

                listener(watchedMovies)
            }

            override fun onCancelled(error: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    fun removeWatchedMovie(
            watchedMovieId: String,
            onComplete: (success: Boolean) -> Unit
    ) {
        val watchedMovieRef = FirebaseDatabase
                .getInstance()
                .reference
                .child("watched_movies")
                .child(getUserId())
                .child(watchedMovieId)
        watchedMovieRef.setValue(null).addOnCompleteListener { task ->
            onComplete(task.isSuccessful)
        }
    }

    fun updateWatchedMovie(
            watchedMovie: WatchedMovie,
            onComplete: (success: Boolean) -> Unit
    ) {
        val watchedMovieRef = FirebaseDatabase
                .getInstance()
                .reference
                .child("watched_movies")
                .child(getUserId())
                .child(watchedMovie.uid)
        watchedMovieRef.setValue(watchedMovie).addOnCompleteListener { task ->
            onComplete(task.isSuccessful)
        }
    }
}