package com.fivegmag.a5gmscommonlibrary

import com.fivegmag.a5gmscommonlibrary.consumptionReporting.ConsumptionRequest
import com.fivegmag.a5gmscommonlibrary.helpers.ClientDataReportingModes
import com.fivegmag.a5gmscommonlibrary.session.ClientDataReportingEntry
import com.fivegmag.a5gmscommonlibrary.session.MediaPlayerEntryContext
import com.fivegmag.a5gmscommonlibrary.session.ServiceLocationUrls
import com.fivegmag.a5gmscommonlibrary.session.PlaybackRequest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class PlaybackRequestUnitTest {
    private fun request(mode: String? = null) =
        PlaybackRequest(ArrayList(), ConsumptionRequest(), ArrayList(), "s", clientDataReportingMode = mode)

    // TS 26.512 V19.3.0 table 12.4-1: the values of client-data-reporting-mode
    @Test
    fun theModesAreThoseOfTheServiceUrl() {
        assertEquals("cmcd-headers", ClientDataReportingModes.CMCD_HEADERS)
        assertEquals("cmcd-query", ClientDataReportingModes.CMCD_QUERY)
    }

    @Test
    fun aPlaybackRequestCarriesTheModeOfTheServiceUrl() {
        assertEquals(ClientDataReportingModes.CMCD_QUERY, request(ClientDataReportingModes.CMCD_QUERY).clientDataReportingMode)
    }

    @Test
    fun withoutAModeTheFieldIsNull() {
        assertNull(request().clientDataReportingMode)
    }

    @Test
    fun aPlaybackRequestCarriesTheClientDataReportingOfTheMediaPlayerEntry() {
        val entry = ClientDataReportingEntry(arrayListOf("http://as.example/dist1/"), arrayListOf("v"), ClientDataReportingModes.CMCD_HEADERS, "s1", "c1", arrayListOf("br", "bl"))
        val request = PlaybackRequest(ArrayList(), ConsumptionRequest(), ArrayList(), "s", clientDataReporting = arrayListOf(entry))
        assertEquals(entry, request.clientDataReporting!!.single())
        assertNull(request().clientDataReporting)
        assertNull(ClientDataReportingEntry().serviceLocationUrls)
        assertNull(ClientDataReportingEntry().otherServiceLocationUrls)
    }

    @Test
    fun anEntryCarriesTheUrlsOfTheServiceLocationsItDoesNotName() {
        val entry = ClientDataReportingEntry(arrayListOf("http://as.example/"), keys = arrayListOf("br"), otherServiceLocationUrls = arrayListOf("http://as.example/dist2/"))
        assertEquals(listOf("http://as.example/dist2/"), entry.otherServiceLocationUrls)
        assertEquals(entry, entry.copy())
    }

    @Test
    fun aPlaybackRequestCarriesWhatThePlayerNeedsOfTheMediaPlayerEntry() {
        val context = MediaPlayerEntryContext(arrayListOf(ServiceLocationUrls("dist1", arrayListOf("http://as.example/dist1/"))), arrayListOf("0", "1"), arrayListOf(1L))
        val request = PlaybackRequest(ArrayList(), ConsumptionRequest(), ArrayList(), "s", mediaPlayerEntryContext = context)
        assertEquals(context, request.mediaPlayerEntryContext)
        assertNull(request().mediaPlayerEntryContext)
        assertEquals(emptyList<String>(), MediaPlayerEntryContext().adaptationSetIds)
        val entry = ClientDataReportingEntry(serviceDescriptionId = 7L, adaptationSets = arrayListOf(0L))
        assertEquals(7L, entry.serviceDescriptionId)
        assertEquals(listOf(0L), entry.adaptationSets)
        assertNull(ClientDataReportingEntry().adaptationSets)
        assertEquals(false, ClientDataReportingEntry().activatedByEvent)
        assertEquals(true, ClientDataReportingEntry(activatedByEvent = true).activatedByEvent)
    }
}
