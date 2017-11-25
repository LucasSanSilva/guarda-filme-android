package com.guardafilme.ui.searchmovie

import com.guardafilme.data.MoviesRepository
import com.guardafilme.model.Movie
import org.junit.Before
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.junit.Assert.assertThat
import org.mockito.*
import org.hamcrest.core.IsEqual.equalTo
import org.mockito.Mockito.*

/**
 * Created by lucassantos on 20/11/17.
 */
class SearchMoviePresenterTest {

    lateinit var showMoviesArgumentCaptor: ArgumentCaptor<List<Movie>>

    @Mock
    lateinit var moviesRepository: MoviesRepository

    @Mock
    lateinit var searchMovieView: SearchMovieContract.View

    lateinit var searchMoviePresenter: SearchMovieContract.Presenter

    private fun <T> anyObject(): T {
        return Mockito.any<T>()
    }

    lateinit var movies: List<Movie>

    inline fun <reified T : Any> argumentCaptor() = ArgumentCaptor.forClass(T::class.java)

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        showMoviesArgumentCaptor = argumentCaptor<List<Movie>>()

        movies = mutableListOf(
                Movie(1, "teste 1", "teste 1", "1998"),
                Movie(2, "teste 2", "teste 2", "1998"),
                Movie(3, "teste 3", "teste 3", "1998"),
                Movie(4, "teste 4", "teste 4", "1998")
        )

        Mockito.`when`(moviesRepository.searchMovies(anyObject(), anyObject())).thenAnswer { invocation ->
            val listener: (List<Movie>) -> Unit = invocation.arguments[0] as (List<Movie>) -> Unit
            listener.invoke(movies)
        }

        searchMoviePresenter = SearchMoviePresenter(moviesRepository)
        searchMoviePresenter.attach(searchMovieView)
    }

    fun searchMovies_shouldWorkCorrectly() {
        searchMoviePresenter.searchMovies("teste")

        verify(searchMovieView).
    }

}