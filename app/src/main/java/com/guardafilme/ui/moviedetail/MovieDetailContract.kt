package com.guardafilme.ui.moviedetail

import com.guardafilme.base.BasePresenter
import com.guardafilme.base.BaseView
import com.guardafilme.model.Movie

interface MovieDetailContract {

    interface View: BaseView {
        fun showMovieData(movie: Movie)
    }

    interface Presenter: BasePresenter<View> {
        fun setTmdbKey(key: String)
        fun setMovie(movieId: Int)
    }

}