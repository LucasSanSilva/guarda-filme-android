package com.guardafilme.ui.searchmovie

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import kotlinx.android.synthetic.main.activity_search_movie.*
import com.guardafilme.R
import com.guardafilme.model.Movie
import dagger.android.AndroidInjection
import org.jetbrains.anko.sdk25.coroutines.onQueryTextListener
import java.util.*
import javax.inject.Inject


/**
 * Created by lucassantos on 06/08/17.
 */
class SearchMovieActivity: AppCompatActivity(), SearchView.OnQueryTextListener, SearchMovieContract.View {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, SearchMovieActivity::class.java)
        }
    }

    @Inject
    lateinit var presenter: SearchMovieContract.Presenter

    val DELAY: Long = 500 // milliseconds

    private lateinit var mAdapter: SearchMovieAdapter
    private lateinit var mSearchView: SearchView

    private var timer: Timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movie)
        presenter.attach(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.title_search_movies)

        val moviesRecyclerView = movies_recycler_view
        moviesRecyclerView.layoutManager = LinearLayoutManager(this)

        mAdapter = SearchMovieAdapter(this, { movie, watchedDate, rate ->
            presenter.movieClicked(movie, watchedDate, rate)
        })
        moviesRecyclerView.adapter = mAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu.findItem(R.id.search_item)

        mSearchView = searchItem.actionView as SearchView
        mSearchView.setOnQueryTextListener(this)
        mSearchView.setIconifiedByDefault(false)
        mSearchView.requestFocus()

        return true
    }

    override fun onStop() {
        presenter.dettach()
        super.onStop()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        mSearchView.clearFocus()
        presenter.searchMovies(getString(R.string.tmdb_key), query)

        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        timer.cancel()
        timer = Timer()
        timer.schedule(
            object : TimerTask() {
                override fun run() {
                    runOnUiThread {
                        presenter.searchMovies(getString(R.string.tmdb_key), newText)
                    }
                }
            },
            DELAY
        )

        return true
    }

    override fun addMovies(movies: List<Movie>?) {
        mAdapter.setItems(movies as List<Movie>)
    }

    override fun showLoading() {
        loading_view.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading_view.visibility = View.GONE
    }

    override fun showMoviesList() {
        movies_recycler_view.visibility = View.VISIBLE
    }

    override fun hideMoviesList() {
        movies_recycler_view.visibility = View.GONE
    }

    override fun finishWithSuccess() {
        setResult(Activity.RESULT_OK)
        finish()
    }
}