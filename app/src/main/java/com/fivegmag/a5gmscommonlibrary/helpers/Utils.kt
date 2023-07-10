package com.fivegmag.a5gmscommonlibrary.helpers

import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

class Utils {

    fun getCurrentTimestamp() : Long {
        return System.currentTimeMillis();
    }

    fun convertTimestampToXsDateTime(timestampInMillis: Long): String {
        // Create a Date object using the provided timestamp
        val date = Date(timestampInMillis)

        // Create a SimpleDateFormat object to format the date
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")

        // Format the date to xs:datetime string
        return dateFormat.format(date)
    }

    fun getCurrentXsDateTime() : String {
        val currentTimestamp = getCurrentTimestamp()

        return convertTimestampToXsDateTime(currentTimestamp)
    }
}