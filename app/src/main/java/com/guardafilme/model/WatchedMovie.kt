package com.guardafilme.model

import com.google.firebase.database.Exclude
import java.util.*

/**
 * Created by lucassantos on 06/08/17.
 */
data class WatchedMovie(
        val uid: String,
        val movieId: Int,
        val title: String,
        val originalTitle: String,
        val watchedDate: Long,
        val poster: String = "",
        val backdrop: String = "",
        val rate: Float = 0F) {
    constructor() : this("", -1, "", "", -1)

    @Exclude
    fun getWatchedDateAsCalendar(): Calendar {
        val watchedCalendar = Calendar.getInstance()
        watchedCalendar.timeInMillis = watchedDate
        return watchedCalendar
    }
}