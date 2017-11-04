package com.guardafilme.ui.welcome

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.guardafilme.MainActivity
import com.guardafilme.R
import com.guardafilme.data.AuthProvider
import com.guardafilme.model.WatchedMovie
import com.guardafilme.ui.UiUtils
import com.guardafilme.ui.searchmovie.SearchMovieActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_welcome.*
import javax.inject.Inject

/**
 * Created by lucassantos on 05/08/17.
 */
class WelcomeActivity: AppCompatActivity(), WelcomeContract.View {
    companion object {
        val USER_ID_EXTRA = "USER_ID_EXTRA"
        val ADD_MOVIE_REQUEST = 435

        fun createIntent(context: Context, userId: String): Intent {
            val intent = Intent(context, WelcomeActivity::class.java)
            intent.putExtra(USER_ID_EXTRA, userId)
            return intent
        }
    }

    private lateinit var mAdapter: WatchedMoviesAdapter

    @Inject
    lateinit var presenter: WelcomeContract.Presenter

    @Inject
    lateinit var authProvider: AuthProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        presenter.attach(this)

        // Setup view
        setContentView(R.layout.activity_welcome)
        supportActionBar?.title = getString(R.string.title_watched_movies)
        fab.setOnClickListener {
            presenter.addMovie()
        }

        // Setup RecyclerView
        val layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        val moviesRecyclerView = watched_movies_recycler_view
        moviesRecyclerView.layoutManager = layoutManager
        mAdapter = WatchedMoviesAdapter(this, object : WatchedMoviesAdapter.WatchedMovieCallback {
            override fun editClicked(watchedMovie: WatchedMovie) {
                editWatchedMovie(watchedMovie)
            }

            override fun removeClicked(watchedMovie: WatchedMovie) {
                presenter.deleteMovie(watchedMovie)
            }
        })
        moviesRecyclerView.adapter = mAdapter

        presenter.load()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_MOVIE_REQUEST && resultCode == Activity.RESULT_OK) {
            presenter.load()
        }
    }

    override fun addWatchedMovies(watchedMovies: List<WatchedMovie>?) {
        mAdapter.setItems(watchedMovies)
    }

    override fun showLoading() {
        loading_view.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading_view.visibility = View.GONE
    }

    override fun showMoviesList() {
        main_layout.visibility = View.VISIBLE
    }

    override fun hideMoviesList() {
        main_layout.visibility = View.GONE
    }

    override fun showTooltip() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideTooltip() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showAddMovie() {
        val intent = SearchMovieActivity.createIntent(this)
        startActivityForResult(intent, ADD_MOVIE_REQUEST)
    }

    private fun editWatchedMovie(watchedMovie: WatchedMovie) {
        UiUtils.showDatePickerDialog(this, { date ->
            UiUtils.showRateDialog(this, { rate ->
                presenter.editMovie(watchedMovie, date, rate)
            }, {
                presenter.editMovie(watchedMovie, date)
            })

        })
    }
}