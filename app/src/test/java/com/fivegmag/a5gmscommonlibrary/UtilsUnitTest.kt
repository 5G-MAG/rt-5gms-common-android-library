package com.fivegmag.a5gmscommonlibrary

import com.fivegmag.a5gmscommonlibrary.helpers.HostInfoTypes
import com.fivegmag.a5gmscommonlibrary.helpers.Utils
import com.fivegmag.a5gmscommonlibrary.models.EndpointAddress
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
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
            assertEquals(8888, endpointAddress.portNumber)
            assertNull(endpointAddress.ipv4Addr)
            assertNull(endpointAddress.ipv6Addr)
        }
    }
    @Test
    fun getEndpointAddressByRequestUrlIp4() {
        val url = "http://192.168.1.1:8888/akamai/bbb_30fps/bbb_30fps.mpd"
        val utils = Utils()
        val endpointAddress = utils.getEndpointAddressByRequestUrl(url)

        assertNotNull(endpointAddress)

        if (endpointAddress != null) {
            assertEquals("192.168.1.1", endpointAddress.ipv4Addr)
            assertEquals(8888, endpointAddress.portNumber)
            assertNull(endpointAddress.domainName)
            assertNull(endpointAddress.ipv6Addr)
        }
    }
    @Test
    fun getEndpointAddressByRequestUrlI6() {
        val url = "http://[1080:0:0:0:8:800:200C:417A]:8888/akamai/bbb_30fps/bbb_30fps.mpd"
        val utils = Utils()
        val endpointAddress = utils.getEndpointAddressByRequestUrl(url)

        assertNotNull(endpointAddress)

        if (endpointAddress != null) {
            assertEquals("[1080:0:0:0:8:800:200C:417A]", endpointAddress.ipv6Addr)
            assertEquals(8888, endpointAddress.portNumber)
            assertNull(endpointAddress.domainName)
            assertNull(endpointAddress.ipv4Addr)
        }
    }


}