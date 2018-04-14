package com.app.weatherapp.utils

import android.net.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Utilities {

    companion object {
        @Throws(ParseException::class)
        fun formatToYesterdayOrToday(date: String): String {
            val dateTime = SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(date)
            val calendar = Calendar.getInstance()
            calendar.time = dateTime
            val today = Calendar.getInstance()
            val yesterday = Calendar.getInstance()
            yesterday.add(Calendar.DATE, 1)

            return if (calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR) && calendar.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)) {
                "Today"
            } else if (calendar.get(Calendar.YEAR) == yesterday.get(Calendar.YEAR) && calendar.get(Calendar.DAY_OF_YEAR) == yesterday.get(Calendar.DAY_OF_YEAR)) {
                "Tomorrow"
            } else {
                SimpleDateFormat("dd MMM yyyy", Locale.US).format(dateTime)
            }
        }
    }

}