package lucas.guardafilme

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.SearchView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_search_movie.*
import kotlinx.android.synthetic.main.item_movie.view.*
import lucas.guardafilme.data.TempDataStore
import lucas.guardafilme.model.Movie
import java.util.*


/**
 * Created by lucassantos on 06/08/17.
 */
class SearchMovieActivity: AppCompatActivity(), SearchView.OnQueryTextListener {

    class MovieHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun handleClick(context: Context, movie: Movie, clickListener: (movie: Movie, watchedDate: Long) -> Unit) {
            itemView.setOnClickListener {
                val datePickerDialog = DatePickerDialog(context)
                datePickerDialog.show()
                datePickerDialog.setOnDateSetListener { datePicker, year, month, day ->
                    val calendar = Calendar.getInstance()
                    calendar.set(year, month, day)
                    val watchedDate = calendar.timeInMillis
                    clickListener(movie, watchedDate)
                }
            }
        }

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
                holder.handleClick(this@SearchMovieActivity,
                        movie,
                        { movie, watchedDate ->
                            TempDataStore.addWatchedMove(this@SearchMovieActivity, movie.uid, movie.title, watchedDate)
                            this@SearchMovieActivity.finish()
                        })
                holder.setTitle(movie.title)
                holder.setYear(movie.year)
            }
        }
        moviesRecyclerView.adapter = mAdapter
    }

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