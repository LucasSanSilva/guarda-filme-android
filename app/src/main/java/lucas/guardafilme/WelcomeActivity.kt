package lucas.guardafilme

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.firebase.ui.auth.IdpResponse
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_welcome.*
import kotlinx.android.synthetic.main.item_watched_movie.view.*
import lucas.guardafilme.model.WatchedMovie
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by lucassantos on 05/08/17.
 */
class WelcomeActivity: AppCompatActivity() {

    class WatchedMovieHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun handleClick(movie: WatchedMovie) {
            itemView.setOnClickListener {  }
        }

        fun setTitle(title: String) {
            itemView.title_text_view.text = title
        }

        fun setWatchedDate(date: Long) {
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val formattedDate = sdf.format(Date(date))
            itemView.watched_date_text_view.text = formattedDate
        }
    }

    private lateinit var mAdapter: FirebaseRecyclerAdapter<WatchedMovie, WatchedMovieHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        supportActionBar?.title = getString(R.string.title_watched_movies)

        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser != null) {
            val fabButton = fab
            fabButton.setOnClickListener {
                val intent = SearchMovieActivity.createIntent(this)
                startActivity(intent)
            }

            val layoutManager = LinearLayoutManager(this)
            layoutManager.reverseLayout = true
            layoutManager.stackFromEnd = true

            val moviesRecyclerView = watched_movies_recycler_view
            moviesRecyclerView.layoutManager = layoutManager

            val databaseRef = FirebaseDatabase
                    .getInstance()
                    .reference
                    .child("watched_movies")
                    .child(currentUser.uid)
                    .orderByChild("watchedDate")

            mAdapter = object : FirebaseRecyclerAdapter<WatchedMovie, WatchedMovieHolder>(
                    WatchedMovie::class.java,
                    R.layout.item_watched_movie,
                    WatchedMovieHolder::class.java,
                    databaseRef) {
                override fun populateViewHolder(holder: WatchedMovieHolder, watchedMovie: WatchedMovie, position: Int) {
                    holder.setTitle(watchedMovie.title)
                    holder.setWatchedDate(watchedMovie.watchedDate)
                }
            }
            moviesRecyclerView.adapter = mAdapter
        }
    }

    companion object {
        fun createIntent(context: Context, response: IdpResponse?): Intent {
            val intent = Intent(context, WelcomeActivity::class.java)
            return intent
        }
    }

}