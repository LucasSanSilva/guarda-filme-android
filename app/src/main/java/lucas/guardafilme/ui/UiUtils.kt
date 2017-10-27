package lucas.guardafilme.ui

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import lucas.guardafilme.data.TempDataStore
import java.util.*

/**
 * Created by lucassantos on 27/10/17.
 */
class UiUtils {
    companion object {
        fun showDatePickerDialog(context: Context, dateSelectedLister: (Long) -> Unit) {
            val currentDate = Calendar.getInstance()

            val datePickerDialog = DatePickerDialog(
                    context,
                    { _, year, month, day ->
                        val calendar = Calendar.getInstance()
                        calendar.set(year, month, day)
                        val watchedDate = calendar.timeInMillis
                        dateSelectedLister(watchedDate)
                    },
                    currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }
    }
}