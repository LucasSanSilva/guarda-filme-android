package lucas.guardafilme.ui.welcome

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_welcome.*
import lucas.guardafilme.MainActivity
import lucas.guardafilme.R
import lucas.guardafilme.data.AuthProvider
import lucas.guardafilme.model.WatchedMovie
import lucas.guardafilme.ui.searchmovie.SearchMovieActivity
import javax.inject.Inject

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

    @Inject
    lateinit var authProvider: AuthProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        // Setup view
        setContentView(R.layout.activity_welcome)
        supportActionBar?.title = getString(R.string.title_watched_movies)
        fab.setOnClickListener {
            val intent = SearchMovieActivity.createIntent(this)
            startActivity(intent)
        }

        // Setup RecyclerView
        val layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        val moviesRecyclerView = watched_movies_recycler_view
        moviesRecyclerView.layoutManager = layoutManager
        mAdapter = WatchedMoviesAdapter(this, object : WatchedMoviesAdapter.WatchedMovieCallback {
            override fun editClicked(watchedMovie: WatchedMovie) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun removeClicked(watchedMovie: WatchedMovie) {
                viewModel.removeWatchedMovie(watchedMovie)
            }
        })
        moviesRecyclerView.adapter = mAdapter

        // Setup ViewModel
        val userId = intent.getStringExtra(USER_ID_EXTRA)
        viewModel = ViewModelProviders.of(this).get(WelcomeViewModel::class.java)
        viewModel.getWatchedMovies().observe(this, Observer { watchedMovies ->
            mAdapter.setItems(watchedMovies)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout_item) {
            authProvider.logoutUser(this, {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            })
        }

        return super.onOptionsItemSelected(item)
    }
}