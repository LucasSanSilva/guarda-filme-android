package lucas.guardafilme

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.View
import android.widget.SearchView
import info.movito.themoviedbapi.TmdbApi
import kotlinx.android.synthetic.main.activity_search_movie.*
import lucas.guardafilme.adapter.SearchMovieAdapter
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


/**
 * Created by lucassantos on 06/08/17.
 */
class SearchMovieActivity: AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var mAdapter: SearchMovieAdapter
    private lateinit var mSearchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movie)

        val moviesRecyclerView = movies_recycler_view
        moviesRecyclerView.layoutManager = LinearLayoutManager(this)

        mAdapter = SearchMovieAdapter(this)
        moviesRecyclerView.adapter = mAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        mSearchView = menu.findItem(R.id.search_item).actionView as SearchView
        mSearchView.setOnQueryTextListener(this)

        return true
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        mSearchView.clearFocus()
        movies_recycler_view.visibility = View.GONE
        loading_view.visibility = View.VISIBLE
        doAsync {
            val searchResult = TmdbApi(getString(R.string.tmdb_key)).search.searchMovie(query, 0, "pt-BR", true, 0)
            uiThread {
                mAdapter.setItems(searchResult.results)
                movies_recycler_view.visibility = View.VISIBLE
                loading_view.visibility = View.GONE
            }
        }

        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        return false
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, SearchMovieActivity::class.java)
        }
    }
}