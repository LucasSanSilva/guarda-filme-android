package com.guardafilme.data

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.guardafilme.model.Movie
import com.guardafilme.model.WatchedMovie

/**
 * Created by lucas.silva on 10/6/17.
 */
class TempDataStore {
    companion object {
        private val WATCHED_MOVIES_REF = "watched_movies"

        fun addWatchedMove(movie: Movie, watchedDate: Long) {
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
                        movie.backdrop
                )
                watchedRef.setValue(watchedMovie)
            }
        }
    }
}