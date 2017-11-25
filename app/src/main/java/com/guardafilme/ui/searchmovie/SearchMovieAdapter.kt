package com.guardafilme.ui.searchmovie

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_movie.view.*
import com.guardafilme.R
import com.guardafilme.model.Movie
import com.guardafilme.ui.UiUtils

/**
 * Created by lucassantos on 11/10/17.
 */
class SearchMovieAdapter(
        private val context: Context,
        private val onMovieSelected: (movie: Movie, watchedDate: Long, rate: Float) -> Unit
): RecyclerView.Adapter<SearchMovieAdapter.SearchMovieViewHolder>() {

    var movies: List<Movie> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return SearchMovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SearchMovieViewHolder, position: Int) {
        holder.bindItem(movies[position], context, onMovieSelected)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun setItems(movieItems: List<Movie>) {
        movies = movieItems
        notifyDataSetChanged()
    }

    class SearchMovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindItem(
                movie: Movie,
                context: Context,
                onMovieSelected: (movie: Movie, watchedDate: Long, rate: Float) -> Unit
        ) {

            itemView.title_text_view.text = movie.title
            itemView.year_text_view.text = movie.year

            itemView.setOnClickListener {
                UiUtils.showDatePickerDialog(context, { watchedDate ->
                    UiUtils.showRateDialog(context, { rate ->
                        Log.d("Teste", "MAOE " + rate.toString())
                        onMovieSelected(movie, watchedDate, rate)
                    }, {
                        onMovieSelected(movie, watchedDate, 0F)
                    })
                })
            }
        }

    }

}