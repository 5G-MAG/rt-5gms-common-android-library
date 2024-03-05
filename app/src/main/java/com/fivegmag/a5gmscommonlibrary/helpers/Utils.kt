package com.fivegmag.a5gmscommonlibrary.helpers

import android.content.res.AssetManager
import java.util.Properties
import java.text.SimpleDateFormat
import java.time.Duration
import java.util.Date
import java.util.Random
import java.util.TimeZone
import okhttp3.Headers
import java.io.InputStream
import java.net.NetworkInterface
import java.time.Instant
import java.util.Locale
import java.util.UUID

class Utils {

    private fun getCurrentTimestamp(): Long {
        return System.currentTimeMillis()
    }

    private fun convertTimestampToXsDateTime(timestampInMillis: Long): String {
        // Create a Date object using the provided timestamp
        val date = Date(timestampInMillis)

        // Create a SimpleDateFormat object to format the date
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")

        // Format the date to xs:datetime string
        return dateFormat.format(date)
    }

    fun generateUUID(): String {
        val uuid = UUID.randomUUID()
        return uuid.toString()
    }

    fun formatDateToOpenAPIFormat(date: Date): String {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        return format.format(date)
    }

    fun calculateTimestampDifferenceInSeconds(timestamp1: String, timestamp2: String): Long {
        val instant1 = Instant.parse(timestamp1)
        val instant2 = Instant.parse(timestamp2)
        val duration = Duration.between(instant1, instant2)
        return duration.seconds
    }

    fun getCurrentXsDateTime(): String {
        val currentTimestamp = getCurrentTimestamp()

        return convertTimestampToXsDateTime(currentTimestamp)
    }

    fun millisecondsToISO8601(milliseconds: Long): String {
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

    fun hasResponseChanged(
        headers: Headers,
        previousResponseHeaders: Headers?
    ): Boolean {
        if (previousResponseHeaders == null) {
            return true
        }
        val headersToValidate = arrayOf("last-modified", "etag")

        return headersToValidate.any { header ->
            hasHeaderChanged(
                headers.get(header),
                previousResponseHeaders.get(header)
            )
        }
    }

    private fun hasHeaderChanged(headerA: String?, headerB: String?): Boolean {
        return headerA == null || headerB == null || headerA != headerB
    }

    fun getIpAddress(ipVer: Int): String? {
        val interfaces = NetworkInterface.getNetworkInterfaces()
        while (interfaces.hasMoreElements()) {
            val networkInterface = interfaces.nextElement()
            val addresses = networkInterface.inetAddresses
            while (addresses.hasMoreElements()) {
                val address = addresses.nextElement()
                if (!address.isLoopbackAddress && address.isSiteLocalAddress) {
                    if ((ipVer == 4 && address.hostAddress.contains("."))||
                        (ipVer == 6 && address.hostAddress.contains(":"))
                    ){
                        return address.hostAddress?.toString()
                    }
                }
            }
        }

        return null
    }

    fun loadConfiguration(theAsset : AssetManager, file: String) : Properties {
        val configProperties =  Properties()
        try {
            val inputStream: InputStream = theAsset.open(file)
            configProperties.loadFromXML(inputStream)
            inputStream.close()
        } catch (e: Exception) {
            print("loadConfiguration Exception: $e")
        }

        return  configProperties
    }

}