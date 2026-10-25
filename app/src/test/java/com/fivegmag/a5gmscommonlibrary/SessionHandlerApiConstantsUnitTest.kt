package com.fivegmag.a5gmscommonlibrary

import com.fivegmag.a5gmscommonlibrary.helpers.SessionHandlerEvents
import com.fivegmag.a5gmscommonlibrary.helpers.SessionHandlerMessageTypes
import com.fivegmag.a5gmscommonlibrary.helpers.SessionHandlingStates
import org.junit.Assert.assertEquals
import org.junit.Test

class SessionHandlerApiConstantsUnitTest {
    // Every message type is told apart by its number
    @Test
    fun messageTypesAreDistinct() {
        val numbers = SessionHandlerMessageTypes::class.java.declaredFields
            .filter { it.type == Int::class.javaPrimitiveType }
            .map { it.getInt(null) }
        assertEquals(numbers.size, numbers.toSet().size)
    }

    // TS 26.510 V19.2.0 tables 11.2.3-1, 11.2.3-2 and 11.2.3-3: the names are those of the specification
    @Test
    fun namesAreThoseOfTheSpecification() {
        assertEquals("ACTIVE", SessionHandlingStates.ACTIVE)
        assertEquals("ERRORED", SessionHandlingStates.ERRORED)
        assertEquals("SESSION_HANDLING_ACTIVATED", SessionHandlerEvents.Notification.SESSION_HANDLING_ACTIVATED)
        assertEquals("SESSION_HANDLING_TERMINATED", SessionHandlerEvents.Notification.SESSION_HANDLING_TERMINATED)
        assertEquals("STREAMING_ACCESS_UPDATED", SessionHandlerEvents.Notification.STREAMING_ACCESS_UPDATED)
        assertEquals("ERROR_SESSION_HANDLING", SessionHandlerEvents.Error.ERROR_SESSION_HANDLING)
        assertEquals("CONSUMPTION_REPORTING_ACTIVATED", SessionHandlerEvents.ConsumptionReporting.CONSUMPTION_REPORTING_ACTIVATED)
        assertEquals("CONSUMPTION_REPORTING_STOPPED", SessionHandlerEvents.ConsumptionReporting.CONSUMPTION_REPORTING_STOPPED)
        assertEquals("NEW_CONSUMPTION_REPORT", SessionHandlerEvents.ConsumptionReporting.NEW_CONSUMPTION_REPORT)
        assertEquals("ERROR_CONSUMPTION_REPORTING", SessionHandlerEvents.Error.ERROR_CONSUMPTION_REPORTING)
        assertEquals("METRICS_REPORTING_ACTIVATED", SessionHandlerEvents.MetricsReporting.METRICS_REPORTING_ACTIVATED)
        assertEquals("METRICS_REPORTING_STOPPED", SessionHandlerEvents.MetricsReporting.METRICS_REPORTING_STOPPED)
        assertEquals("NEW_METRICS_REPORT", SessionHandlerEvents.MetricsReporting.NEW_METRICS_REPORT)
        assertEquals("ERROR_METRICS_REPORTING", SessionHandlerEvents.Error.ERROR_METRICS_REPORTING)
    }
}
