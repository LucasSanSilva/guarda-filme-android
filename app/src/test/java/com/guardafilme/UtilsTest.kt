package com.guardafilme

import org.hamcrest.core.IsEqual.equalTo
import org.junit.Test
import org.junit.Assert.assertThat

/**
 * Created by lucassantos on 12/10/17.
 */
class UtilsTest {
    @Test
    fun getYearFromMovieReleaseDate_shouldReturnCorrectYear() {
        assertThat(Utils.getYearFromMovieReleaseDate("2017-10-12"), equalTo("2017"))
        assertThat(Utils.getYearFromMovieReleaseDate("2015-10-12"), equalTo("2015"))
    }

    @Test
    fun getYearFromMovieReleaseDate_shouldReturnEmptyInInvalidDate() {
        assertThat(Utils.getYearFromMovieReleaseDate("2017"), equalTo(""))
        assertThat(Utils.getYearFromMovieReleaseDate(""), equalTo(""))
        assertThat(Utils.getYearFromMovieReleaseDate("2321412-123-213"), equalTo(""))
    }
}