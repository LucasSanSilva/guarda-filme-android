package lucas.guardafilme.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import lucas.guardafilme.model.WatchedMovie
import javax.inject.Inject

/**
 * Created by lucassantos on 17/10/17.
 */
class UserRepository @Inject constructor() {
    fun getWatchedMovies(userId: String): LiveData<List<WatchedMovie>> {
        val data: MutableLiveData<List<WatchedMovie>> = MutableLiveData()
        data.value

        val databaseRef = FirebaseDatabase
                .getInstance()
                .reference
                .child("watched_movies")
                .child(userId)
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

                data.postValue(watchedMovies)
            }

            override fun onCancelled(error: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        return data
    }
}