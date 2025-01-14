/*
License: 5G-MAG Public License (v1.0)
Author: Daniel Silhavy
Copyright: (C) 2023 Fraunhofer FOKUS
For full license terms please see the LICENSE file distributed with this
program. If this file is missing then the license can be retrieved from
https://drive.google.com/file/d/1cinCiA778IErENZ3JN52VFW-1ffHpx7Z/view
*/

package com.fivegmag.a5gmscommonlibrary.helpers

import android.content.res.AssetManager
import java.util.Properties
import com.fivegmag.a5gmscommonlibrary.models.EndpointAddress
import com.fivegmag.a5gmscommonlibrary.models.HostInfo
import java.text.SimpleDateFormat
import java.time.Duration
import java.util.Date
import java.util.Random
import java.util.TimeZone
import okhttp3.Headers
import java.io.InputStream
import java.net.NetworkInterface
import java.net.URL
import java.time.Instant
import java.util.Locale
import java.util.UUID

class Utils {

    fun getCurrentTimestamp(): Long {
        return System.currentTimeMillis()
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

    fun generateUuidToHexBinary(): String {
        val uuid = this.generateUUID()
        return uuid.replace("-", "")
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
                    if ((ipVer == 4 && address?.hostAddress?.contains(".") == true) ||
                        (ipVer == 6 && address?.hostAddress?.contains(":") == true)
                    ) {
                        return address.hostAddress?.toString()
                    }
                }
            }
        }

        return null
    }

    fun getHostInfo(urlString: String): HostInfo? {
        try {
            val url = URL(urlString)
            val host = url.host

            // Check if it's a valid IPv4 address
            val ipv4Regex = """^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$""".toRegex()
            if (ipv4Regex.matches(host)) {
                return HostInfo(HostInfoTypes.IP_V4, host)
            }

            // Check if it's a valid IPv6 address
            val ipv6Regex = """^\[([0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}\]$""".toRegex()
            if (ipv6Regex.matches(host)) {
                return HostInfo(HostInfoTypes.IP_V6, host)
            }

            // Remove leading "www." from the domain
            val domain = if (host.startsWith("www.")) host.substring(4) else host

            // Assume it's a domain name
            return HostInfo(HostInfoTypes.DOMAIN_NAME, domain)
        } catch (e: Exception) {
            return null
        }
    }

    fun getPort(url: String): Int? {
        try {
            val urlInstance = URL(url)
            val port = urlInstance.port
            if (port == -1) {
                return urlInstance.defaultPort
            }

            return port
        } catch (e: Exception) {
            return null
        }
    }

    fun getEndpointAddressByRequestUrl(
        requestUrl: String
    ): EndpointAddress? {
        try {
            val hostInfo = getHostInfo(requestUrl)
            val port = getPort(requestUrl)

            if (hostInfo != null && port != null) {
                return when (hostInfo.type) {
                    HostInfoTypes.IP_V4 -> EndpointAddress(null, hostInfo.host, null, port)
                    HostInfoTypes.IP_V6 -> EndpointAddress(null, null, hostInfo.host, port)
                    HostInfoTypes.DOMAIN_NAME -> EndpointAddress(hostInfo.host, null, null, port)
                    else -> null
                }
            }

            return null

        } catch (e: Exception) {
            return null
        }

    }

    fun loadConfiguration(assetManager: AssetManager, file: String): Properties {
        val configProperties = Properties()
        try {
            val inputStream: InputStream = assetManager.open(file)
            configProperties.loadFromXML(inputStream)
            inputStream.close()
        } catch (e: Exception) {
            print("loadConfiguration Exception: $e")
        }

        return configProperties
    }

}