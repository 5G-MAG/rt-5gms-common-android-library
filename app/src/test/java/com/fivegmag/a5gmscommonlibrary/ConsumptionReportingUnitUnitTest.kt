package com.fivegmag.a5gmscommonlibrary

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fivegmag.a5gmscommonlibrary.consumptionReporting.ConsumptionReportingUnit
import com.fivegmag.a5gmscommonlibrary.models.Snssai
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

// TS 26.510 V19.2.0 table 9.6.3.2-1: sliceInfo (Snssai) and dataNetworkName (Dnn) are optional properties of a unit
class ConsumptionReportingUnitUnitTest {
    private val mapper = jacksonObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
        .setFilterProvider(SimpleFilterProvider().setFailOnUnknownId(false))

    private fun json(unit: ConsumptionReportingUnit) = mapper.readTree(mapper.writeValueAsString(unit))

    @Test
    fun theSliceAndTheDataNetworkAreWrittenWhenKnown() {
        val unit = ConsumptionReportingUnit("c|v1", startTime = "2026-11-12T10:00:00Z", duration = 3, sliceInfo = Snssai(1, "00007B"), dataNetworkName = "internet")
        val node = json(unit)
        assertEquals(1, node.get("sliceInfo").get("sst").asInt())
        assertEquals("00007B", node.get("sliceInfo").get("sd").asText())
        assertEquals("internet", node.get("dataNetworkName").asText())
    }

    @Test
    fun theyAreLeftOutWhenNotKnown() {
        val node = json(ConsumptionReportingUnit("v1", startTime = "2026-11-12T10:00:00Z", duration = 0))
        assertFalse(node.has("sliceInfo"))
        assertFalse(node.has("dataNetworkName"))
    }
}
