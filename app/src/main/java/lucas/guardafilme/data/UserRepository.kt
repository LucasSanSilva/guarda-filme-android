package lucas.guardafilme.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import lucas.guardafilme.model.WatchedMovie

/**
 * Created by lucassantos on 17/10/17.
 */
class UserRepository {
    fun getWatchedMovies(userId: String): LiveData<List<WatchedMovie>> {
        val data: MutableLiveData<List<WatchedMovie>> = MutableLiveData()

        val databaseRef = FirebaseDatabase
                .getInstance()
                .reference
                .child("watched_movies")
                .child(userId)
                .orderByChild("watchedDate")
        databaseRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                // TODO
            }

            override fun onCancelled(error: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        return data
    }
}