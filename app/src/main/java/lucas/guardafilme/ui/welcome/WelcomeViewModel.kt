package lucas.guardafilme.ui.welcome

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import lucas.guardafilme.data.UserRepository
import lucas.guardafilme.di.DaggerViewModelComponent
import lucas.guardafilme.model.WatchedMovie
import javax.inject.Inject

/**
 * Created by lucassantos on 17/10/17.
 */
class WelcomeViewModel: ViewModel() {

    @Inject
    lateinit var userRepository: UserRepository

    val userId: String
    private var watchedMovies: MutableLiveData<List<WatchedMovie>>? = null

    init {
        DaggerViewModelComponent
                .builder()
                .build()
                .inject(this)

        userId = userRepository.getUserId()
    }

    fun loadWatchedMovies() {
        userRepository.getWatchedMovies(userId, { loadedWatchedMovies ->
            watchedMovies?.postValue(loadedWatchedMovies)
        })
    }

    fun getWatchedMovies(): LiveData<List<WatchedMovie>> {
        if (watchedMovies == null) {
            watchedMovies = MutableLiveData()
            loadWatchedMovies()
        }

        return watchedMovies as LiveData<List<WatchedMovie>>
    }

    fun removeWatchedMovie(watchedMovie: WatchedMovie) {
        userRepository.removeWatchedMovie(userId, watchedMovie.uid, { success ->
            if (success) {
                loadWatchedMovies()
            }
        })
    }

    fun updateWatchedMovie(watchedMovie: WatchedMovie, updatedDate: Long) {
        val updatedWatchedMovie = watchedMovie.copy(watchedDate = updatedDate)
        userRepository.updateWatchedMovie(userId, updatedWatchedMovie, { success ->
            if (success) {
                loadWatchedMovies()
            }
        })
    }
}