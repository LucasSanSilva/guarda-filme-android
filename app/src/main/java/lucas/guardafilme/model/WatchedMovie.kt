package lucas.guardafilme.model

/**
 * Created by lucassantos on 06/08/17.
 */
data class WatchedMovie(val uid: String, val movieId: Int, val movieName: String, val watchedDate: Long) {
    constructor() : this("", -1, "", -1)
}