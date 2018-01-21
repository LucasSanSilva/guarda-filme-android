package com.guardafilme.ui.welcome

import android.content.Context
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
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
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val MOVIE_TYPE: Int = 0
    val AD_TYPE: Int = 1

    val AD_POSITION: Int = 4

    interface WatchedMovieCallback {
        fun editClicked(watchedMovie: WatchedMovie)
        fun removeClicked(watchedMovie: WatchedMovie)
    }

    class AdViewHolder(itemView: View, val adView: AdView): RecyclerView.ViewHolder(itemView)

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
        if (position == AD_POSITION) {
            return AD_TYPE
        }
        return MOVIE_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == MOVIE_TYPE) {
            return onCreateWatchedMovieViewHolder(parent)
        }

        return onCreateAdViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position != AD_POSITION) {
            if (position > AD_POSITION) {
                (holder as WatchedMovieViewHolder).bindItem(watchedMovies[position - 1])
            } else {
                (holder as WatchedMovieViewHolder).bindItem(watchedMovies[position])
            }
        } else {
            val adRequest = AdRequest.Builder().build()
            (holder as AdViewHolder).adView.loadAd(adRequest)
        }
    }

    override fun getItemCount(): Int {
        return watchedMovies.size
    }

    private fun onCreateWatchedMovieViewHolder(parent: ViewGroup): WatchedMovieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_watched_movie, parent, false)

        val posterWidth = Utils.getScreenWidth(context) / 2
        val posterHeight = Constants.POSTER_RATIO * posterWidth

        val layoutParams = itemView.poster_image_view.layoutParams
        layoutParams.height = posterHeight.toInt()
        itemView.poster_image_view.layoutParams = layoutParams

        return WatchedMovieViewHolder(itemView, context, watchedMovieCallback)
    }

    private fun onCreateAdViewHolder(parent: ViewGroup): AdViewHolder {
        val adView = AdView(context)
        adView.adSize = AdSize.BANNER
        adView.adUnitId = context.getString(R.string.ad_unit)

        return AdViewHolder(adView, adView)
    }

    fun setItems(movieItems: List<WatchedMovie>?) {
        if (movieItems != null) {
            watchedMovies = movieItems
            notifyDataSetChanged()
        }
    }

}