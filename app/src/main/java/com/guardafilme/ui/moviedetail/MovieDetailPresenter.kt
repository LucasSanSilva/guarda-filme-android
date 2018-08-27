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
        moviesRepository.getMovieDetails(apiKey, movieId, { movie ->
            view?.showMovieData(movie)
        })
    }

}