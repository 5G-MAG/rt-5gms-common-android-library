package com.fivegmag.a5gmscommonlibrary

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
    fun getDomainName() {
        val url = "http://dash.akamaized.net/akamai/bbb_30fps/bbb_30fps.mpd"
        val utils = Utils()
        val domainName = utils.getDomainName(url)

        assertEquals("dash.akamaized.net", domainName)
    }

    @Test
    fun getEndpointAddressByRequestUrl() = runBlocking {
        val url = "https://livesim.dashif.org/livesim2/testpic_2s/V300/852746919.m4s"
        val utils = Utils()
        val deferredEndpointAddress = CompletableDeferred<EndpointAddress?>()
        utils.getEndpointAddressByRequestUrl(url) {endpointAddress ->
            deferredEndpointAddress.complete(endpointAddress)
        }
        val endpointAddress = deferredEndpointAddress.await()
        if (endpointAddress != null) {
            assertEquals("livesim.dashif.org", endpointAddress.domainName)
            assertEquals(443, endpointAddress.portNumber)
            assertNotNull(endpointAddress.ipv4Addr)
        }
    }
}