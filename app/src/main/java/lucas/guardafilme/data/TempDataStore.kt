package lucas.guardafilme.data

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import lucas.guardafilme.model.WatchedMovie

/**
 * Created by lucas.silva on 10/6/17.
 */
class TempDataStore {
    companion object {
        val WATCHED_MOVIES_REF = "watched_movies"

        fun addWatchedMove(context: Context, movieId: String, movieTitle: String, watchedDate: Long) {
            val currentUser = FirebaseAuth.getInstance().currentUser

            if (currentUser != null) {
                val watchedRef = FirebaseDatabase
                        .getInstance()
                        .reference
                        .child(WATCHED_MOVIES_REF)
                        .child(currentUser.uid)
                        .push()

                val watchedMovie = WatchedMovie(watchedRef.key, movieId, movieTitle, watchedDate)
                watchedRef.setValue(watchedMovie)
            }
        }
    }
}