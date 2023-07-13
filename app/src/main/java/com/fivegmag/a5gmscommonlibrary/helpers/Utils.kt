package com.fivegmag.a5gmscommonlibrary.helpers

import java.text.SimpleDateFormat
import java.time.Duration
import java.util.Date
import java.util.Random
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

    fun millisecondsToISO8601(milliseconds: Long): String? {
        // Create a Duration object from milliseconds
        val duration: Duration = Duration.ofMillis(milliseconds)

        // Extract the components from the duration
        val years: Long = duration.toDays() / 365
        val days: Long = duration.toDays() % 365
        val hours: Long = duration.toHours() % 24
        val minutes: Long = duration.toMinutes() % 60
        val seconds: Long = duration.seconds % 60

        // Construct the ISO 8601 period string
        return "P" + years + "Y" + days + "D" +
                "T" + hours + "H" + minutes + "M" + seconds + "S"
    }

    fun generateRandomFloat(): Float {
        val random = Random()
        return random.nextFloat() * 100.0f
    }

    fun addTrailingSlashIfNeeded(input: String): String {
        return if (!input.endsWith("/")) {
            "$input/"
        } else {
            input
        }
    }
}