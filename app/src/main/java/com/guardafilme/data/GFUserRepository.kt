package com.guardafilme.data

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
 * Created by lucassantos on 03/11/17.
 */
class GFUserRepository @Inject constructor(): UserRepository {
    private val WATCHED_MOVIES_REF = "watched_movies"

    override fun getCurrentUser(): User? {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            return User(currentUser.uid, currentUser.displayName)
        }

        return null
    }

    override fun getUserId(): String {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            return currentUser.uid
        }
        return ""
    }

    override fun getWatchedMovies(listener: (List<WatchedMovie>) -> Unit) {
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

    override fun deleteWatchedMovie(
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

    override fun updateWatchedMovie(
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

    override fun addWatchedMove(
            movie: Movie,
            watchedDate: Long,
            rate: Float,
            onComplete: (success: Boolean) -> Unit
    ) {
        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser != null) {
            val watchedRef = FirebaseDatabase
                    .getInstance()
                    .reference
                    .child(WATCHED_MOVIES_REF)
                    .child(currentUser.uid)
                    .push()

            val watchedMovie = WatchedMovie(
                    watchedRef.key,
                    movie.id,
                    movie.title,
                    movie.originalTitle,
                    watchedDate,
                    movie.poster,
                    movie.backdrop,
                    rate
            )
            watchedRef.setValue(watchedMovie)
        }
    }
}