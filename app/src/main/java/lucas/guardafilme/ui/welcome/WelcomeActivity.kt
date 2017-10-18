package lucas.guardafilme.ui.welcome

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_welcome.*
import kotlinx.android.synthetic.main.item_watched_movie.view.*
import lucas.guardafilme.MainActivity
import lucas.guardafilme.R
import lucas.guardafilme.SearchMovieActivity
import lucas.guardafilme.model.WatchedMovie
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by lucassantos on 05/08/17.
 */
class WelcomeActivity: AppCompatActivity() {

    companion object {
        val USER_ID_EXTRA = "USER_ID_EXTRA"

        fun createIntent(context: Context, userId: String): Intent {
            val intent = Intent(context, WelcomeActivity::class.java)
            intent.putExtra(USER_ID_EXTRA, userId)
            return intent
        }
    }

    private lateinit var mAdapter: WatchedMoviesAdapter
    private lateinit var viewModel: WelcomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val userId = intent.getStringExtra(USER_ID_EXTRA)

        supportActionBar?.title = getString(R.string.title_watched_movies)
        viewModel = ViewModelProviders.of(this).get(WelcomeViewModel::class.java)
        viewModel.init(userId)

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

        mAdapter = WatchedMoviesAdapter()
        viewModel.watchedMovies.observe(this, android.arch.lifecycle.Observer { watchedMovies ->
            mAdapter.setItems(watchedMovies)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout_item) {
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener { task ->
                        startActivity(Intent(this, MainActivity::class.java))
                    }
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

}