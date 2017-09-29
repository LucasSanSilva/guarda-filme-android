package lucas.guardafilme.model

/**
 * Created by lucassantos on 06/08/17.
 */
data class Movie(val uid: String, val title: String, val year: Int) {
    constructor() : this("", "", -1)
}