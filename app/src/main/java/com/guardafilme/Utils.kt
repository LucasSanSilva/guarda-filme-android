package com.guardafilme

/**
 * Created by lucassantos on 12/10/17.
 */
class Utils {
    companion object {
        /**
         * Retorna o ano dado uma data no formato yyyy-MM-dd
         */
        fun getYearFromMovieReleaseDate(strDate: String): String {
            val splitDate = strDate.split("-")
            if (splitDate.size < 3 || splitDate.size > 3) {
                return ""
            }

            if (splitDate[0].length != 4) {
                return ""
            }

            return splitDate[0]
        }
    }
}