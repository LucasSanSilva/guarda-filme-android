package com.guardafilme.ui.moviedetail

import com.guardafilme.model.Movie
import javax.inject.Inject

class MovieDetailPresenter @Inject constructor(): MovieDetailContract.Presenter {

    var view: MovieDetailContract.View? = null

    override fun attach(view: MovieDetailContract.View) {
        this.view = view
    }

    override fun dettach() {
        this.view = null
    }

    override fun setMovie(movieId: Int) {
        val movie = Movie(
                24,
                "Filme Teste",
                "Teste Movie",
                "1988",
                "/oj0ibkqKGJ3CvSTb3Pkx299P0SK.jpg",
                "/kkS8PKa8c134vXsj2fQkNqOaCXU.jpg"
        )

        view?.showMovieData(movie)
    }

}