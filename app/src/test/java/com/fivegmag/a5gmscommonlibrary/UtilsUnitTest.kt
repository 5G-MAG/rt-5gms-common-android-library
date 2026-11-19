package com.fivegmag.a5gmscommonlibrary

import com.fivegmag.a5gmscommonlibrary.helpers.HostInfoTypes
import com.fivegmag.a5gmscommonlibrary.helpers.Utils
import org.junit.Test
import org.junit.Assert.*

class UtilsUnitTest {

    @Test
    fun getHttpsPort() {
        val url = "https://dash.akamaized.net/akamai/bbb_30fps/bbb_30fps.mpd"
        val utils = Utils()
        val port = utils.getPort(url)

        assertEquals(443, port)
    }

    @Test
    fun getHttpPort() {
        val url = "http://dash.akamaized.net/akamai/bbb_30fps/bbb_30fps.mpd"
        val utils = Utils()
        val port = utils.getPort(url)

        assertEquals(80, port)
    }

    @Test
    fun getHostInfoIp4() {
        val url = "http://192.168.1.1/akamai/bbb_30fps/bbb_30fps.mpd"
        val utils = Utils()
        val hostInfo = utils.getHostInfo(url)

        assertNotNull(hostInfo)

        if (hostInfo != null) {
            assertEquals("192.168.1.1", hostInfo.host)
            assertEquals(HostInfoTypes.IP_V4, hostInfo.type)
        }
    }

    @Test
    fun getHostInfoIp6() {
        val url = "http://[1080:0:0:0:8:800:200C:417A]/akamai/bbb_30fps/bbb_30fps.mpd"
        val utils = Utils()
        val hostInfo = utils.getHostInfo(url)

        assertNotNull(hostInfo)

        if (hostInfo != null) {
            assertEquals("[1080:0:0:0:8:800:200C:417A]", hostInfo.host)
            assertEquals(HostInfoTypes.IP_V6, hostInfo.type)
        }
    }

    @Test
    fun getHostInfoDomain() {
        val url = "http://dash.akamaized.net/akamai/bbb_30fps/bbb_30fps.mpd"
        val utils = Utils()
        val hostInfo = utils.getHostInfo(url)

        assertNotNull(hostInfo)

        if (hostInfo != null) {
            assertEquals("dash.akamaized.net", hostInfo.host)
            assertEquals(HostInfoTypes.DOMAIN_NAME, hostInfo.type)
        }
    }
    @Test
    fun getEndpointAddressByRequestUrlDomain() {
        val url = "http://dash.akamaized.net:8888/akamai/bbb_30fps/bbb_30fps.mpd"
        val utils = Utils()
        val endpointAddress = utils.getEndpointAddressByRequestUrl(url)

        assertNotNull(endpointAddress)

        if (endpointAddress != null) {
            assertEquals("dash.akamaized.net", endpointAddress.domainName)
            assertEquals(listOf(8888), endpointAddress.portNumbers)
        }
    }
    @Test
    fun getEndpointAddressByRequestUrlIp4() {
        val url = "http://192.168.1.1:8888/akamai/bbb_30fps/bbb_30fps.mpd"
        val utils = Utils()
        val endpointAddress = utils.getEndpointAddressByRequestUrl(url)

        assertNotNull(endpointAddress)

        if (endpointAddress != null) {
            // the table of TS 26.510 7.3.3.11 has no IP address property: the textual address is carried in domainName
            assertEquals("192.168.1.1", endpointAddress.domainName)
            assertEquals(listOf(8888), endpointAddress.portNumbers)
        }
    }
    @Test
    fun getEndpointAddressByRequestUrlI6() {
        val url = "http://[1080:0:0:0:8:800:200C:417A]:8888/akamai/bbb_30fps/bbb_30fps.mpd"
        val utils = Utils()
        val endpointAddress = utils.getEndpointAddressByRequestUrl(url)

        assertNotNull(endpointAddress)

        if (endpointAddress != null) {
            assertEquals("[1080:0:0:0:8:800:200C:417A]", endpointAddress.domainName)
            assertEquals(listOf(8888), endpointAddress.portNumbers)
        }
    }

    @Test
    fun generateUuidToHexBinary() {
        val utils = Utils()
        val uuid = utils.generateUuidToHexBinary()
        assertEquals("java.lang.String", uuid.javaClass.name)
    }

    // the Z of a DateTime is UTC: the value must not follow the time zone of the device
    @Test
    fun formatDateToOpenAPIFormatIsUtcInEveryTimeZone() {
        val saved = java.util.TimeZone.getDefault()
        try {
            val instant = java.util.Date(1795082400000L) // 2026-11-19T10:00:00Z
            for (zone in listOf("Europe/Zurich", "America/Los_Angeles", "Asia/Kolkata", "UTC")) {
                java.util.TimeZone.setDefault(java.util.TimeZone.getTimeZone(zone))
                assertEquals(zone, "2026-11-19T10:00:00Z", Utils().formatDateToOpenAPIFormat(instant))
            }
        } finally {
            java.util.TimeZone.setDefault(saved)
        }
    }

    // the consumption reporting units use the difference of two such values, which is parsed as UTC
    @Test
    fun formatDateToOpenAPIFormatRoundTripsThroughInstant() {
        val saved = java.util.TimeZone.getDefault()
        try {
            java.util.TimeZone.setDefault(java.util.TimeZone.getTimeZone("America/Los_Angeles"))
            val utils = Utils()
            val a = utils.formatDateToOpenAPIFormat(java.util.Date(1795082400000L))
            val b = utils.formatDateToOpenAPIFormat(java.util.Date(1795082407000L))
            assertEquals(7L, utils.calculateTimestampDifferenceInSeconds(a, b))
            assertEquals(1795082400000L, java.time.Instant.parse(a).toEpochMilli())
        } finally {
            java.util.TimeZone.setDefault(saved)
        }
    }
}
