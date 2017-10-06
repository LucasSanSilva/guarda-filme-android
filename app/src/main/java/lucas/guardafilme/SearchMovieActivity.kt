package lucas.guardafilme

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.View
import android.widget.LinearLayout
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_search_movie.*
import kotlinx.android.synthetic.main.item_movie.view.*
import lucas.guardafilme.model.Movie
import android.support.v4.widget.SearchViewCompat.setOnQueryTextListener
import android.support.v4.widget.SearchViewCompat.setQueryHint
import android.support.v4.view.MenuItemCompat.getActionView
import android.view.MenuInflater
import android.widget.SearchView
import android.support.v4.view.MenuItemCompat.getActionView
import android.app.SearchManager
import android.support.v7.widget.Toolbar


/**
 * Created by lucassantos on 06/08/17.
 */
class SearchMovieActivity: AppCompatActivity(), SearchView.OnQueryTextListener {

    class MovieHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun setTitle(title: String) {
            itemView.title_text_view.text = title
        }

        fun setYear(year: Int) {
            itemView.year_text_view.text = year.toString()
        }
    }

    lateinit var mAdapter: FirebaseRecyclerAdapter<Movie, MovieHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movie)

        val toolbar: Toolbar = toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)

        val moviesRecyclerView = movies_recycler_view
        moviesRecyclerView.layoutManager = LinearLayoutManager(this)

        val databaseRef = FirebaseDatabase.getInstance().reference.child("movies")

        mAdapter = object: FirebaseRecyclerAdapter<Movie, MovieHolder>(
                Movie::class.java,
                R.layout.item_movie,
                MovieHolder::class.java,
                databaseRef) {
            override fun populateViewHolder(holder: MovieHolder, movie: Movie, position: Int) {
                holder.setTitle(movie.title)
                holder.setYear(movie.year)
            }
        }
        moviesRecyclerView.adapter = mAdapter
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        super.onCreateOptionsMenu(menu)
//
//        val inflater = menuInflater
//        inflater.inflate(R.menu.menu_search, menu)
//
//        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
//        val searchView = menu?.findItem(R.id.search_item)?.actionView as SearchView?
//
//        if (searchView != null) {
//            searchView.setSearchableInfo(
//                    searchManager.getSearchableInfo(componentName))
//            searchView.queryHint = getString(R.string.action_search)
//
//            searchView.setOnQueryTextListener(this)
//            searchView.setIconifiedByDefault(false)
//        }
//
//        return true
//    }

    override fun onDestroy() {
        super.onDestroy()
        mAdapter.cleanup()
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        fun createIntent(context: Context): Intent {
            val intent = Intent(context, SearchMovieActivity::class.java)
            return intent
        }
    }
}