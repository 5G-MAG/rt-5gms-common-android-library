package com.fivegmag.a5gmscommonlibrary

import com.fivegmag.a5gmscommonlibrary.helpers.MediaPlayerCapabilities
import com.fivegmag.a5gmscommonlibrary.helpers.SessionHandlerBundleKeys
import com.fivegmag.a5gmscommonlibrary.helpers.SessionHandlerEvents
import com.fivegmag.a5gmscommonlibrary.helpers.SessionHandlerMessageTypes
import com.fivegmag.a5gmscommonlibrary.helpers.SessionHandlingStates
import com.fivegmag.a5gmscommonlibrary.helpers.UserAgentTokens
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

    // TS 26.510 V19.2.0 tables 11.3.2-2 and 11.3.2-3: the names of the Dynamic Policy events and error events
    @Test
    fun dynamicPolicyNamesAreThoseOfTheSpecification() {
        assertEquals("POLICY_ACTIVATED", SessionHandlerEvents.DynamicPolicy.POLICY_ACTIVATED)
        assertEquals("POLICY_DEACTIVATED", SessionHandlerEvents.DynamicPolicy.POLICY_DEACTIVATED)
        assertEquals("BACKGROUND_DATA_TRANSFER_OPPORTUNITY", SessionHandlerEvents.DynamicPolicy.BACKGROUND_DATA_TRANSFER_OPPORTUNITY)
        assertEquals("L4S_ENABLED", SessionHandlerEvents.DynamicPolicy.L4S_ENABLED)
        assertEquals("QOS_MONITORING_ENABLED", SessionHandlerEvents.DynamicPolicy.QOS_MONITORING_ENABLED)
        assertEquals("QOS_MONITORING_RESULTS", SessionHandlerEvents.DynamicPolicy.QOS_MONITORING_RESULTS)
        assertEquals("ERROR_INVALID_SERVICE_OPERATION_POINT", SessionHandlerEvents.Error.ERROR_INVALID_SERVICE_OPERATION_POINT)
        assertEquals("ERROR_UNAUTHORISED", SessionHandlerEvents.Error.ERROR_UNAUTHORISED)
        assertEquals("ERROR_BACKGROUND_DATA_TRANSFER", SessionHandlerEvents.Error.ERROR_BACKGROUND_DATA_TRANSFER)
    }

    // Tables 11.4.2-2 and 11.4.2-3
    @Test
    fun networkAssistanceNamesAreThoseOfTheSpecification() {
        assertEquals("BIT_RATE_RECOMMENDATION", SessionHandlerEvents.NetworkAssistance.BIT_RATE_RECOMMENDATION)
        assertEquals("ERROR_BIT_RATE_RECOMMENDATION", SessionHandlerEvents.Error.ERROR_BIT_RATE_RECOMMENDATION)
        assertEquals("ERROR_DELIVERY_BOOST", SessionHandlerEvents.Error.ERROR_DELIVERY_BOOST)
    }

    // Every method of clauses 11.3.1 and 11.4.1 has a message and a reply
    @Test
    fun everyMethodHasAMessageAndAReply() {
        val names = SessionHandlerMessageTypes::class.java.declaredFields.filter { it.type == Int::class.javaPrimitiveType }.map { it.name }.toSet()
        for (method in listOf("GET_BDT_INFO", "ACTIVATE_POLICY", "DEACTIVATE_POLICY", "GET_DYNAMIC_POLICY_STATUS",
            "REQUEST_BIT_RATE_RECOMMENDATION", "REQUEST_DELIVERY_BOOST", "GET_SERVICE_OPERATION_POINTS")) {
            assertEquals("$method", true, names.contains(method))
            assertEquals("${method}_REPLY", true, names.contains(method + "_REPLY"))
        }
    }

    @Test
    fun targetBitRateKeysAreDistinctFromTheReturnedOnes() {
        assertEquals("targetDownlinkBitRate", SessionHandlerBundleKeys.TARGET_DOWNLINK_BIT_RATE)
        assertEquals("targetUplinkBitRate", SessionHandlerBundleKeys.TARGET_UPLINK_BIT_RATE)
        assertEquals(false, SessionHandlerBundleKeys.TARGET_DOWNLINK_BIT_RATE == SessionHandlerBundleKeys.RECOMMENDED_DOWNLINK_BIT_RATE)
    }

    // TS 26.512 V19.3.0 clauses 6.2.3.2.2 and 6.2.3.2.3: the product token names the Handler and carries the version of the specification
    @Test
    fun userAgentTokensCarryTheReleaseOfTheSpecification() {
        assertEquals("5GMSMediaSessionHandler/19.3.0", UserAgentTokens.FIVE_G_MS_MEDIA_SESSION_HANDLER)
        assertEquals("5GMSMediaStreamHandler/19.3.0", UserAgentTokens.FIVE_G_MS_MEDIA_STREAM_HANDLER)
    }

    // TS 26.510 V19.2.0 clause 5.3.5.1: a changed client metrics reporting configuration reaches the Media Stream Handler by its own message
    @Test
    fun aCmcdConfigurationUpdateHasItsOwnMessageAndKey() {
        assertEquals(44, SessionHandlerMessageTypes.CMCD_CONFIGURATION_UPDATE)
        assertEquals(false, SessionHandlerMessageTypes.CMCD_CONFIGURATION_UPDATE == SessionHandlerMessageTypes.CMCD_LOCATION_UPDATE)
        assertEquals("cmcdConfigurations", SessionHandlerBundleKeys.CMCD_CONFIGURATIONS)
    }

    // TS 26.512 V19.3.0 table 13.2.4-2: the names of the capabilities are those of the specification; the interrogation has a message and a reply
    @Test
    fun theCapabilitiesAreNamedAsInTable13_2_4_2() {
        assertEquals("CAPABILITY_L4S", MediaPlayerCapabilities.CAPABILITY_L4S)
        assertEquals("CAPABILITY_QOS", MediaPlayerCapabilities.CAPABILITY_QOS)
        assertEquals("capabilities", SessionHandlerBundleKeys.CAPABILITIES)
        assertEquals(45, SessionHandlerMessageTypes.GET_PLAYER_CAPABILITIES)
        assertEquals(46, SessionHandlerMessageTypes.GET_PLAYER_CAPABILITIES_REPLY)
    }

    // TS 26.512 V19.3.0 table 13.2.5-1: the notification of a change of the Service Operation Point has a message of its own
    @Test
    fun aChangeOfTheServiceOperationPointHasAMessage() {
        assertEquals(47, SessionHandlerMessageTypes.OPERATION_POINT_CHANGED)
    }
}
