package lucas.guardafilme.ui.searchmovie

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
import lucas.guardafilme.R
import lucas.guardafilme.adapter.SearchMovieAdapter
import lucas.guardafilme.data.MoviesProvider
import lucas.guardafilme.data.TempDataStore
import lucas.guardafilme.model.Movie


/**
 * Created by lucassantos on 06/08/17.
 */
class SearchMovieActivity: AppCompatActivity(), SearchView.OnQueryTextListener {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, SearchMovieActivity::class.java)
        }
    }

    private val ARG_MOVIES = "ARG_MOVIES"

    private lateinit var mAdapter: SearchMovieAdapter
    private lateinit var mSearchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movie)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.title_search_movies)

        val moviesRecyclerView = movies_recycler_view
        moviesRecyclerView.layoutManager = LinearLayoutManager(this)

        mAdapter = SearchMovieAdapter(this, { movie, watchedDate ->
            TempDataStore.addWatchedMove(movie, watchedDate)
            setResult(Activity.RESULT_OK)
            finish()
        })
        moviesRecyclerView.adapter = mAdapter

        if (savedInstanceState != null) {
            val movies = savedInstanceState.getParcelableArray(ARG_MOVIES)
            if (movies != null) {
                @Suppress("UNCHECKED_CAST")
                mAdapter.setItems(movies.asList() as List<Movie>)
            }
        }
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

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArray(ARG_MOVIES, mAdapter.movies.toTypedArray())

        super.onSaveInstanceState(outState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        mSearchView.clearFocus()
        movies_recycler_view.visibility = View.GONE
        loading_view.visibility = View.VISIBLE
        MoviesProvider.searchMovies(this, query, { movies: List<Movie> ->
            mAdapter.setItems(movies)
            movies_recycler_view.visibility = View.VISIBLE
            loading_view.visibility = View.GONE
        })

        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        return false
    }
}