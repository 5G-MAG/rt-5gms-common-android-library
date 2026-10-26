package com.fivegmag.a5gmscommonlibrary

import com.fivegmag.a5gmscommonlibrary.models.DynamicPolicy
import com.fivegmag.a5gmscommonlibrary.models.NetworkAssistanceSession
import com.fivegmag.a5gmscommonlibrary.models.OperationSuccessResponse
import com.fivegmag.a5gmscommonlibrary.models.ServiceAccessInformation
import com.fivegmag.a5gmscommonlibrary.models.ClientPolicySpecification
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class DynamicPolicyModelsUnitTest {
    private val gson = Gson()

    // TS 26.510 V19.2.0 table 9.3.3.1-1: the read-only properties are present in a response
    @Test
    fun readsADynamicPolicyResponse() {
        val json = """{"dynamicPolicyId":"d1","provisioningSessionId":"ps1","sessionId":"s1","policyTemplateId":"pt1",
            "applicationFlowBindings":[{"componentReference":"video","applicationFlowDescription":{"filterMethod":"5_TUPLE",
            "packetFilter":{"direction":"DOWNLINK","sourceAddress":{"ipv4Addr":"10.0.0.2"},"destinationAddress":{"ipv4Addr":"192.0.2.1"},
            "protocolNumber":6,"sourcePort":0,"destinationPort":0}}}],
            "bdtSpecification":{"estimatedDataTransferVolume":{"totalVolume":1000},"windows":[{"timeWindow":{"startTime":"2026-10-28T10:00:00Z","stopTime":"2026-10-28T11:00:00Z"},"maximumDownlinkBitRate":"5 Mbps"}]},
            "qosEnforcement":true,"l4SEnabled":true,"qoSMonitoringEnabled":true,"qoSMonitoringResults":{"dlDelays":[12],"pdmf":false}}"""
        val policy = gson.fromJson(json, DynamicPolicy::class.java)
        assertEquals("d1", policy.dynamicPolicyId)
        assertEquals("pt1", policy.policyTemplateId)
        assertEquals("192.0.2.1", policy.applicationFlowBindings[0].applicationFlowDescription.packetFilter!!.destinationAddress!!.ipv4Addr)
        assertEquals("2026-10-28T11:00:00Z", policy.bdtSpecification!!.windows[0].timeWindow.stopTime)
        assertEquals("5 Mbps", policy.bdtSpecification!!.windows[0].maximumDownlinkBitRate)
        assertEquals(1000L, policy.bdtSpecification!!.estimatedDataTransferVolume!!.totalVolume)
        assertTrue(policy.l4SEnabled!!)
        assertTrue(policy.qoSMonitoringEnabled!!)
        assertEquals(12, policy.qoSMonitoringResults!!.dlDelays!![0])
    }

    // The properties that the Media AF owns are left out of a request: null properties are not written by Gson
    @Test
    fun aRequestLeavesOutWhatTheMediaAfOwns() {
        val request = DynamicPolicy("s1", "pt1", arrayListOf())
        val written = gson.toJson(request)
        assertFalse(written.contains("dynamicPolicyId"))
        assertFalse(written.contains("l4SEnabled"))
        assertFalse(written.contains("qoSMonitoringEnabled"))
        assertTrue(written.contains("\"sessionId\":\"s1\""))
    }

    // Table 9.4.3.1-1
    @Test
    fun readsANetworkAssistanceSession() {
        val json = """{"naSessionId":"n1","provisioningSessionId":"ps1","sessionId":"s1","policyTemplateId":"pt1","componentReference":"video",
            "applicationFlowDescription":{"filterMethod":"5_TUPLE","packetFilter":{"direction":"DOWNLINK"}},
            "recommendedQoS":{"downlinkBitRates":{"maximumRequestedBitRate":"8 Mbps","minimumDesiredBitRate":"4 Mbps","minimumRequestedBitRate":"4 Mbps"},
            "uplinkBitRates":{"maximumRequestedBitRate":"8 Mbps","minimumDesiredBitRate":"4 Mbps","minimumRequestedBitRate":"4 Mbps"}}}"""
        val session = gson.fromJson(json, NetworkAssistanceSession::class.java)
        assertEquals("n1", session.naSessionId)
        assertEquals("8 Mbps", session.recommendedQoS!!.downlinkBitRates.maximumRequestedBitRate)
        assertNull(session.requestedQoS)
    }

    // Table 7.3.3.9-1
    @Test
    fun readsAnOperationSuccessResponse() {
        assertTrue(gson.fromJson("""{"success":true}""", OperationSuccessResponse::class.java).success)
        assertEquals("no", gson.fromJson("""{"success":false,"reason":"no"}""", OperationSuccessResponse::class.java).reason)
    }

    // Table 9.2.3.1-1: the policy template binding carries the externalReference, the preferences and the offered windows
    @Test
    fun readsTheBindingsOfTheServiceAccessInformation() {
        val json = """{"provisioningSessionId":"ps1","dynamicPolicyInvocationConfiguration":{"serverAddresses":["http://af/3gpp-maf-session-handling/v1/"],
            "sdfMethods":["5_TUPLE"],"policyTemplateBindings":[{"externalReference":"sop-1","policyTemplateId":"pt1","l4SEnablementPreference":true,
            "qoSMonitoringEnablementPreference":true,"bdtWindows":[{"timeWindow":{"startTime":"2026-10-28T10:00:00Z","stopTime":"2026-10-28T11:00:00Z"}}]}]}}"""
        val binding = gson.fromJson(json, ServiceAccessInformation::class.java).dynamicPolicyInvocationConfiguration!!.policyTemplateBindings[0]
        assertEquals("sop-1", binding.externalReference)
        assertTrue(binding.l4SEnablementPreference)
        assertTrue(binding.qoSMonitoringEnablementPreference)
        assertEquals("2026-10-28T10:00:00Z", binding.bdtWindows!![0].timeWindow.startTime)
    }

    @Test
    fun clientPolicySpecificationIsWrittenWithTheNamesOfTheSpecification() {
        val written = gson.toJson(ClientPolicySpecification(
            com.fivegmag.a5gmscommonlibrary.models.UnidirectionalBitRateSpecification("2 Mbps", "1 Mbps", "1 Mbps"),
            com.fivegmag.a5gmscommonlibrary.models.UnidirectionalBitRateSpecification("2 Mbps", "1 Mbps", "1 Mbps")))
        assertTrue(written.contains("\"maximumRequestedBitRate\":\"2 Mbps\""))
        assertTrue(written.contains("downlinkBitRates"))
        assertFalse(written.contains("l4SRequired"))
    }
}
