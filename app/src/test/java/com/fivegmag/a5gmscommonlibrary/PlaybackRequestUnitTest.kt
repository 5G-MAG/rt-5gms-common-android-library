package com.fivegmag.a5gmscommonlibrary

import com.fivegmag.a5gmscommonlibrary.consumptionReporting.ConsumptionRequest
import com.fivegmag.a5gmscommonlibrary.helpers.ClientDataReportingModes
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
}
