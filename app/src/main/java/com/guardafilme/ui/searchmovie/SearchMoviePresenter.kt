package com.guardafilme.ui.searchmovie

import com.guardafilme.data.MoviesRepository
import com.guardafilme.data.UserRepository
import com.guardafilme.model.Movie
import javax.inject.Inject

/**
 * Created by lucassantos on 20/11/17.
 */
class SearchMoviePresenter @Inject constructor(
        val moviesRepository: MoviesRepository,
        val userRepository: UserRepository
): SearchMovieContract.Presenter {

    var view: SearchMovieContract.View? = null

    override fun attach(view: SearchMovieContract.View) {
        this.view = view
    }

    override fun dettach() {
        this.view = null
    }

    override fun searchMovies(query: String) {
        view?.hideMoviesList()
        view?.showLoading()
        moviesRepository.searchMovies(query, { movies ->
            view?.addMovies(movies)
            view?.hideLoading()
            view?.showMoviesList()
        })
    }

    override fun movieClicked(movie: Movie, watchedDate: Long, rate: Float) {
        userRepository.addWatchedMove(movie, watchedDate, rate, { success ->
            view?.finishWithSuccess()
        })
    }

}