package lucas.guardafilme.ui.welcome

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import lucas.guardafilme.data.UserRepository
import lucas.guardafilme.model.WatchedMovie

/**
 * Created by lucassantos on 17/10/17.
 */
class WelcomeViewModel: ViewModel() {

    val userRepository = UserRepository()

    lateinit var userId: String
    lateinit var watchedMovies: LiveData<List<WatchedMovie>>

    fun init(userId: String) {
        this.userId = userId

        watchedMovies = userRepository.getWatchedMovies(userId)
    }

}