package com.guardafilme.ui.welcome

import android.content.Context
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.guardafilme.Constants
import com.guardafilme.Constants.Companion.IMAGES_URL
import kotlinx.android.synthetic.main.item_watched_movie.view.*
import com.guardafilme.R
import com.guardafilme.Utils
import com.guardafilme.model.WatchedMovie
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by lucassantos on 17/10/17.
 */
class WatchedMoviesAdapter(
        val context: Context,
        val watchedMovieCallback: WatchedMovieCallback
): RecyclerView.Adapter<WatchedMoviesAdapter.WatchedMovieViewHolder>() {

    val MOVIE_TYPE: Int = 0
    val AD_TYPE: Int = 1

    interface WatchedMovieCallback {
        fun editClicked(watchedMovie: WatchedMovie)
        fun removeClicked(watchedMovie: WatchedMovie)
    }

    class WatchedMovieViewHolder(
            itemView: View,
            val context: Context,
            val watchedMovieCallback: WatchedMovieCallback
    ): RecyclerView.ViewHolder(itemView) {
        fun bindItem(watchedMovie: WatchedMovie) {
            Glide.with(context)
                    .load(IMAGES_URL + watchedMovie.poster)
                    .apply(RequestOptions()
                            .centerCrop())
                    .into(itemView.poster_image_view)

            itemView.title_text_view.text = watchedMovie.title

            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val formattedDate = sdf.format(Date(watchedMovie.watchedDate))
            itemView.watched_date_text_view.text = formattedDate

            itemView.rating_bar.rating = watchedMovie.rate

            itemView.options_button.setOnClickListener {
                val popupMenu = PopupMenu(context, itemView.options_button)
                popupMenu.inflate(R.menu.menu_watched_movie)
                popupMenu.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.edit_item -> {
                            watchedMovieCallback.editClicked(watchedMovie)
                            true
                        }
                        R.id.remove_item -> {
                            watchedMovieCallback.removeClicked(watchedMovie)
                            true
                        }
                        else -> {
                            false
                        }
                    }
                }
                popupMenu.show()
            }
        }
    }

    var watchedMovies: List<WatchedMovie> = emptyList()

    override fun getItemViewType(position: Int): Int {
        if (position == 4) {
            return AD_TYPE
        }
        return MOVIE_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchedMovieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_watched_movie, parent, false)

        val posterWidth = Utils.getScreenWidth(context) / 2
        val posterHeight = Constants.POSTER_RATIO * posterWidth

        val layoutParams = itemView.poster_image_view.layoutParams
        layoutParams.height = posterHeight.toInt()
        itemView.poster_image_view.layoutParams = layoutParams

        return WatchedMovieViewHolder(itemView, context, watchedMovieCallback)
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

}