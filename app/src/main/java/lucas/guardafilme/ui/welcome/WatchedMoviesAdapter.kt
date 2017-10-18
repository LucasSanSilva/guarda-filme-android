package lucas.guardafilme.ui.welcome

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_watched_movie.view.*
import lucas.guardafilme.R
import lucas.guardafilme.model.WatchedMovie
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by lucassantos on 17/10/17.
 */
class WatchedMoviesAdapter: RecyclerView.Adapter<WatchedMoviesAdapter.WatchedMovieViewHolder>() {

    var watchedMovies: List<WatchedMovie> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchedMovieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_watched_movie, parent, false)
        return WatchedMovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WatchedMovieViewHolder, position: Int) {
        holder.bindItem(watchedMovies[position])
    }

    override fun getItemCount(): Int {
        return watchedMovies.size
    }

    fun setItems(movieItems: List<WatchedMovie>?) {
        if (movieItems != null) {
            watchedMovies = movieItems
            notifyDataSetChanged()
        }
    }

    class WatchedMovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindItem(watchedMovie: WatchedMovie) {
            itemView.title_text_view.text = watchedMovie.title

            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val formattedDate = sdf.format(Date(watchedMovie.watchedDate))
            itemView.watched_date_text_view.text = formattedDate
        }
    }

}