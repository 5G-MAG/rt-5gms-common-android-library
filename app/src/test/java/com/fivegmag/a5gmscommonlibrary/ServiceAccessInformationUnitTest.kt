package com.fivegmag.a5gmscommonlibrary

import com.fivegmag.a5gmscommonlibrary.models.ServiceAccessInformation
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * The Service Access Information as a Media AF of Release 19 sends it (TS 26.510 V19.2.0 table 9.2.3.1-1),
 * parsed with Gson like the Media Session Handler does.
 */
class ServiceAccessInformationUnitTest {
    private val full = """
    {
      "provisioningSessionId": "ps1",
      "provisioningSessionType": "DOWNLINK",
      "locationReporting": true,
      "notficationURL": "http://af.example/notify",
      "streamingAccess": {
        "entryPoints": [
          {"locator": "https://m4.example/a.mpd", "contentType": "application/dash+xml", "profiles": ["urn:p"]},
          {"locator": "https://m4.example/b", "protocol": "urn:3gpp:5gms:content-protocol:http-pull"}
        ],
        "eMBMSServiceAnnouncementLocator": "https://sa.example/usd",
        "mbsExternalServiceIdentifier": "mbs-1"
      },
      "rtcClientConfiguration": {
        "stunEndpoints": [{"domainName": "stun.example", "portNumbers": [3478]}],
        "turnEndpoints": [{"domainName": "turn.example", "portNumbers": [3478],
                           "credentials": {"username": "u", "passphrase": "p"}}]
      },
      "clientConsumptionReportingConfiguration": {
        "serverAddresses": ["https://af.example/m5/consumption"], "accessReporting": true, "samplePercentage": 50.0,
        "reportingInterval": 10, "locationReporting": false
      },
      "dynamicPolicyInvocationConfiguration": {
        "serverAddresses": ["https://af.example/m5/dp"],
        "policyTemplateBindings": [
          {"externalReference": "HD", "policyTemplateId": "t1", "pduSetMarking": true, "l4SEnablementPreference": true}
        ],
        "sdfMethods": ["5_TUPLE", "DOMAIN_NAME"]
      },
      "clientMetricsReportingConfigurations": [
        {"metricsReportingConfigurationId": "m1", "serverAddresses": ["https://af.example/m5/metrics"],
         "scheme": "urn:3GPP:ns:PSS:DASH:QM10", "samplePercentage": 100.0, "urlFilters": [], "samplingPeriod": 5,
         "metrics": ["urn:3GPP:ns:PSS:DASH:QM10#HTTPList"],
         "sliceScope": [{"sst": 1, "sd": "000001"}], "reportingStartOffset": 2, "reportingDuration": 60,
         "positiveCrossingThresholds": {"bitrate": [1.5, 2.5]}, "negativeCrossingThresholds": {"bitrate": [0.5]}}
      ],
      "networkAssistanceConfiguration": {"serverAddresses": ["https://af.example/m5/na"]},
      "clientEdgeResourcesConfiguration": {
        "easDiscoveryTemplate": {"easId": "eas1", "easFeatures": ["f"]},
        "easRelocationRequirements": {"tolerance": "TOLERATE", "maxInterruptionDuration": 3}
      }
    }
    """

    @Test
    fun everyPropertyOfTheRelease19ResourceIsKept() {
        val sai = Gson().fromJson(full, ServiceAccessInformation::class.java)
        assertEquals(true, sai.locationReporting)
        assertEquals("http://af.example/notify", sai.notficationURL)
        assertEquals("https://sa.example/usd", sai.streamingAccess.eMBMSServiceAnnouncementLocator)
        assertEquals("mbs-1", sai.streamingAccess.mbsExternalServiceIdentifier)
        assertEquals("urn:3gpp:5gms:content-protocol:http-pull", sai.streamingAccess.entryPoints!![1].protocol)
        assertNull(sai.streamingAccess.entryPoints!![1].contentType)
        assertEquals("u", sai.rtcClientConfiguration!!.turnEndpoints!![0].credentials!!.username)
        assertEquals(listOf(3478), sai.rtcClientConfiguration!!.stunEndpoints!![0].portNumbers)
        val dp = sai.dynamicPolicyInvocationConfiguration!!
        assertEquals("t1", dp.policyTemplateBindings[0].policyTemplateId)
        assertTrue(dp.policyTemplateBindings[0].pduSetMarking)
        assertEquals(listOf("5_TUPLE", "DOMAIN_NAME"), dp.sdfMethods)
        val metrics = sai.clientMetricsReportingConfigurations!![0]
        assertEquals(1, metrics.sliceScope!![0].sst)
        assertEquals(60L, metrics.reportingDuration)
        assertEquals(listOf(1.5f, 2.5f), metrics.positiveCrossingThresholds!!["bitrate"])
        assertEquals("https://af.example/m5/na", sai.networkAssistanceConfiguration!!.serverAddresses[0])
        assertEquals("eas1", sai.clientEdgeResourcesConfiguration!!.easDiscoveryTemplate.easId)
        assertEquals(3, sai.clientEdgeResourcesConfiguration!!.easRelocationRequirements!!.maxInterruptionDuration)
    }

    @Test
    fun aResourceWithoutTheNewPropertiesParses() {
        val sai = Gson().fromJson(
            """{"provisioningSessionId":"p","provisioningSessionType":"DOWNLINK","locationReporting":false,
                "streamingAccess":{"entryPoints":[{"locator":"https://x/a.mpd","contentType":"application/dash+xml"}]}}""",
            ServiceAccessInformation::class.java
        )
        assertNotNull(sai.streamingAccess.entryPoints)
        assertFalse(sai.locationReporting!!)
        assertNull(sai.dynamicPolicyInvocationConfiguration)
        assertNull(sai.networkAssistanceConfiguration)
    }
}
