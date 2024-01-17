package com.fivegmag.a5gmscommonlibrary.helpers

import com.fivegmag.a5gmscommonlibrary.models.EndpointAddress
import java.text.SimpleDateFormat
import java.time.Duration
import java.util.Date
import java.util.Random
import java.util.TimeZone
import okhttp3.Headers
import java.net.Inet6Address
import java.net.InetAddress
import java.net.NetworkInterface
import java.net.URI
import java.net.URL
import java.time.Instant
import java.util.Locale
import java.util.UUID

class Utils {

    fun getCurrentTimestamp(): Long {
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
                    if ((ipVer == 4 && address.hostAddress.contains(".")) ||
                        (ipVer == 6 && address.hostAddress.contains(":"))
                    ) {
                        return address.hostAddress.toString()
                    }
                }
            }
        }

        return null
    }

    fun getDomainName(url: String): String? {
        return try {
            val uri = URI(url)
            val domain = uri.host
            domain?.removePrefix("www.")
        } catch (e: Exception) {
            null
        }
    }

    fun getPort(url: String): Int? {
        try {
            val url = URL(url)
            val port = url.port
            if (port == -1) {
                return url.defaultPort
            }

            return port
        } catch (e: Exception) {
            return null
        }
    }

    /**
     * Network operations are not allowed on the main thread.
     * Perform the IP lookup in a different thread and then call a callback function
     *
     * @param requestUrl
     * @param callback
     */
    fun getEndpointAddressByRequestUrl(
        requestUrl: String,
        callback: (EndpointAddress?) -> Unit
    ) {
        Thread {
            try {
                val domainName = getDomainName(requestUrl)
                val port = getPort(requestUrl)
                var ipv4Addr: String? = null
                var ipv6Addr: String? = null
                val inetAddresses = InetAddress.getAllByName(domainName)
                for (inetAddress in inetAddresses) {
                    val ipAddress = inetAddress.hostAddress
                    if (inetAddress is Inet6Address) {
                        ipv6Addr = ipAddress
                    } else {
                        ipv4Addr = ipAddress
                    }
                }
                if (port != null) {
                    callback(EndpointAddress(domainName, ipv4Addr, ipv6Addr, port))
                }

                callback(null)

            } catch (e: Exception) {
                callback(null)
            }
        }.start()
    }

}