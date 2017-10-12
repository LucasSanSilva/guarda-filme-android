package lucas.guardafilme.model

/**
 * Created by lucassantos on 06/08/17.
 */
data class WatchedMovie(
        val uid: String,
        val movieId: Int,
        val title: String,
        val originalTitle: String,
        val watchedDate: Long,
        val poster: String = "") {
    constructor() : this("", -1, "", "", -1)
}