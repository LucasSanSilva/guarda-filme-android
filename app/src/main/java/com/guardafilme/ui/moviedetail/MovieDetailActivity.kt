package com.guardafilme.ui.moviedetail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.guardafilme.Constants
import com.guardafilme.R
import com.guardafilme.model.Movie
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_movie_detail.*
import javax.inject.Inject

class MovieDetailActivity: Activity(), MovieDetailContract.View {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, MovieDetailActivity::class.java)
        }
    }

    @Inject
    lateinit var presenter: MovieDetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        presenter.attach(this)

        presenter.setMovie(24)
    }

    override fun showMovieData(movie: Movie) {
        val toolbarImageView = toolbar_image_view
        Glide.with(this)
                .load(Constants.IMAGES_URL + movie.backdrop)
//                .apply(RequestOptions().centerCrop())
                .into(toolbarImageView)
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}