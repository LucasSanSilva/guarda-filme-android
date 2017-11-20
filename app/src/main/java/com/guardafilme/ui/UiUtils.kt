package com.guardafilme.ui

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.widget.RatingBar
import com.guardafilme.R
import com.guardafilme.data.TempDataStore
import org.jetbrains.anko.find
import org.jetbrains.anko.layoutInflater
import java.util.*

/**
 * Created by lucassantos on 27/10/17.
 */
class UiUtils {
    companion object {
        fun showDatePickerDialog(
                context: Context,
                dateSelectedLister: (Long) -> Unit,
                defaultDate: Calendar = Calendar.getInstance()
        ) {
            val datePickerDialog = DatePickerDialog(
                    context,
                    { _, year, month, day ->
                        val calendar = Calendar.getInstance()
                        calendar.set(year, month, day)
                        val watchedDate = calendar.timeInMillis
                        dateSelectedLister(watchedDate)
                    },
                    defaultDate.get(Calendar.YEAR), defaultDate.get(Calendar.MONTH), defaultDate.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }

        fun showRateDialog(
                context: Context,
                rateListener: (Float) -> Unit,
                cancelListener: () -> Unit,
                defaultRate: Float = 0F) {
            val dialogBuilder = AlertDialog.Builder(context)
            val inflater = context.layoutInflater
            val rateLayout = inflater.inflate(R.layout.dialog_rate_movie, null)
            val ratingBar = rateLayout.find<RatingBar>(R.id.rating_bar)
            ratingBar.rating = defaultRate

            dialogBuilder.setView(rateLayout)
                    .setPositiveButton(R.string.action_ok, { dialogInterface, id ->
                        rateListener(ratingBar.rating)
                    })
                    .setNegativeButton(R.string.action_later, { dialogInterface, id ->
                        dialogInterface.dismiss()
                        cancelListener()
                    })
            dialogBuilder.create().show()
        }
    }
}