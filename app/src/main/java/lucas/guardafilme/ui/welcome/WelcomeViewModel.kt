package lucas.guardafilme.ui.welcome

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import lucas.guardafilme.data.AuthRepository
import lucas.guardafilme.data.UserRepository
import lucas.guardafilme.di.DaggerViewModelComponent
import lucas.guardafilme.model.WatchedMovie
import javax.inject.Inject

/**
 * Created by lucassantos on 17/10/17.
 */
class WelcomeViewModel: ViewModel() {
//    @Inject
//    lateinit var authRepository: AuthRepository

    @Inject
    lateinit var userRepository: UserRepository

    var userId: String? = null
    lateinit var watchedMovies: LiveData<List<WatchedMovie>>

    init {
        DaggerViewModelComponent
                .builder()
                .build()
                .inject(this)
    }

    fun init(userId: String) {
        if (this.userId == null) {
            this.userId = userId
            watchedMovies = userRepository.getWatchedMovies(userId)
        }
    }
}