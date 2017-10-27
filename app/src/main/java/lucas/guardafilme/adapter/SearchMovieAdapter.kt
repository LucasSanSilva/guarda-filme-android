package lucas.guardafilme.adapter

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_movie.view.*
import lucas.guardafilme.R
import lucas.guardafilme.data.TempDataStore
import lucas.guardafilme.model.Movie
import lucas.guardafilme.ui.UiUtils
import java.util.*

/**
 * Created by lucassantos on 11/10/17.
 */
class SearchMovieAdapter(private val context: Context): RecyclerView.Adapter<SearchMovieAdapter.SearchMovieViewHolder>() {

    var movies: List<Movie> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return SearchMovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SearchMovieViewHolder, position: Int) {
        holder.bindItem(movies[position], context)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun setItems(movieItems: List<Movie>) {
        movies = movieItems
        notifyDataSetChanged()
    }

    class SearchMovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindItem(movie: Movie, context: Context) {
            itemView.title_text_view.text = movie.title
            itemView.year_text_view.text = movie.year

            itemView.setOnClickListener {
                UiUtils.showDatePickerDialog(context, { watchedDate ->
                    TempDataStore.addWatchedMove(context, movie, watchedDate)
                    (context as Activity).finish()
                })
            }
        }

    }

}