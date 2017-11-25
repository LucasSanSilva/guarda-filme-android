package com.guardafilme.ui.searchmovie

import com.guardafilme.data.MoviesRepository
import com.guardafilme.model.Movie
import javax.inject.Inject

/**
 * Created by lucassantos on 20/11/17.
 */
class SearchMoviePresenter @Inject constructor(val moviesRepository: MoviesRepository): SearchMovieContract.Presenter {

    var view: SearchMovieContract.View? = null

    override fun attach(view: SearchMovieContract.View) {
        this.view = view
    }

    override fun dettach() {
        this.view = null
    }

    override fun searchMovies(query: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun movieClicked(movie: Movie) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}