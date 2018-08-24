package com.guardafilme.ui.moviedetail

import com.guardafilme.data.MoviesRepository
import com.guardafilme.model.Movie
import javax.inject.Inject

class MovieDetailPresenter @Inject constructor(val moviesRepository: MoviesRepository): MovieDetailContract.Presenter {

    var view: MovieDetailContract.View? = null
    var apiKey: String = ""

    override fun attach(view: MovieDetailContract.View) {
        this.view = view
    }

    override fun dettach() {
        this.view = null
    }

    override fun setTmdbKey(key: String) {
        this.apiKey = key
    }

    override fun setMovie(movieId: Int) {
        moviesRepository.getMovieDetails(apiKey, movieId, { movieDb ->

        })

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