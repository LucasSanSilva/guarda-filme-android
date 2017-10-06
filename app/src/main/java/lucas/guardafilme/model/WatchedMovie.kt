package lucas.guardafilme.model

/**
 * Created by lucassantos on 06/08/17.
 */
data class WatchedMovie(val uid: String, val movieId: String, val movieName: String, val watchedDate: Long) {
    constructor() : this("", "", "", -1)
}