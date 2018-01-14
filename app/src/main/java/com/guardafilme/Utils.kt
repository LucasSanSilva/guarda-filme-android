package com.guardafilme

import android.content.Context
import android.content.Context.WINDOW_SERVICE
import android.view.WindowManager
import android.util.DisplayMetrics



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

        fun getScreenWidth(context: Context): Int {
            val dm = DisplayMetrics()
            val windowManager = context.getSystemService(WINDOW_SERVICE) as WindowManager
            windowManager.defaultDisplay.getMetrics(dm)
            return dm.widthPixels
        }
    }
}