package com.fivegmag.a5gmscommonlibrary

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fivegmag.a5gmscommonlibrary.helpers.CmcdSchemes
import com.fivegmag.a5gmscommonlibrary.helpers.MetricReportingSchemes
import com.fivegmag.a5gmscommonlibrary.helpers.VrMetrics
import com.fivegmag.a5gmscommonlibrary.qoeMetricsReporting.CompQualLatencyEntry
import com.fivegmag.a5gmscommonlibrary.qoeMetricsReporting.QoeReport
import com.fivegmag.a5gmscommonlibrary.qoeMetricsReporting.QualityLevelEntry
import com.fivegmag.a5gmscommonlibrary.qoeMetricsReporting.RenderedViewportEntry
import com.fivegmag.a5gmscommonlibrary.qoeMetricsReporting.ViewportData
import com.fivegmag.a5gmscommonlibrary.qoeMetricsReporting.ViewportItem
import com.fivegmag.a5gmscommonlibrary.qoeMetricsReporting.VrDeviceInformationEntry
import com.fivegmag.a5gmscommonlibrary.qoeMetricsReporting.VrMetric
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/** The virtual reality metrics of TS 26.118 V19.0.0 clause 9 in the names of TS 26.512 V19.3.0 clause E.2.2 and in the report of clause 9.4.3 */
class VrMetricsUnitTest {
    // clause E.2.2: the terms are the key names of clause 9.3 below the name space of the vocabulary
    @Test
    fun theTermsAreTheKeyNamesOfClause93() {
        assertEquals("urn:3gpp:metadata:2020:VR:metrics#CompQualLatency", VrMetrics.COMP_QUAL_LATENCY)
        assertEquals("urn:3gpp:metadata:2020:VR:metrics#RenderedViewports", VrMetrics.RENDERED_VIEWPORTS)
        assertEquals("urn:3gpp:metadata:2020:VR:metrics#VrDeviceInformation", VrMetrics.VR_DEVICE_INFORMATION)
        assertEquals(setOf("CompQualLatency", "RenderedViewports", "VrDeviceInformation"), VrMetrics.KEYS)
        assertEquals(VrMetrics.NAMESPACE, MetricReportingSchemes.THREE_GPP_VR_METRIC_REPORTING)
        assertEquals(1, VrMetrics.SCHEMA_VERSION)
    }

    private val mapper = XmlMapper()

    // clause 9.4.3: the elements are in the order of the schema, all qualified
    @Test
    fun theDeviceInformationIsOneElementPerValueInTheOrderOfTheSchema() {
        val entry = VrDeviceInformationEntry("2026-11-19T10:00:00.000Z", "PT0S", "brand model 15", 1, 2, 3, 4, 5, 6, 60)
        val xml = mapper.writeValueAsString(VrMetric(vrDeviceInformation = arrayListOf(entry)))
        assertEquals(
            "<VrMetric><vr:vrDeviceInformation><vr:start>2026-11-19T10:00:00.000Z</vr:start><vr:mstart>PT0S</vr:mstart>" +
                "<vr:deviceIdentifier>brand model 15</vr:deviceIdentifier><vr:horizontalResolution>1</vr:horizontalResolution>" +
                "<vr:verticalResolution>2</vr:verticalResolution><vr:horizontalFoV>3</vr:horizontalFoV><vr:verticalFoV>4</vr:verticalFoV>" +
                "<vr:renderedHorizontalFoV>5</vr:renderedHorizontalFoV><vr:renderedVerticalFoV>6</vr:renderedVerticalFoV>" +
                "<vr:refreshRate>60</vr:refreshRate></vr:vrDeviceInformation></VrMetric>",
            xml
        )
    }

    @Test
    fun theViewportMetricsHaveTheElementsOfTheSchema() {
        val viewport = ViewportData(10, 20, 30, 40, 50)
        val item = ViewportItem(viewport, arrayListOf(QualityLevelEntry(60.0, 1, 3840, 2160), QualityLevelEntry(40.0, 2, 960, 540)))
        val latency = CompQualLatencyEntry(item, item, item, "2026-11-19T10:00:00.000Z", "PT1S", 120, 10, arrayListOf(0, 2))
        val xml = mapper.writeValueAsString(VrMetric(compQualLatency = arrayListOf(latency)))
        assertTrue(xml, xml.contains("<vr:firstViewport><vr:position><vr:centreAzimuth>10</vr:centreAzimuth><vr:centreElevation>20</vr:centreElevation>"))
        assertTrue(xml, xml.contains("<vr:qualityLevel><vr:coverage>60.0</vr:coverage><vr:qr>1</vr:qr><vr:width>3840</vr:width><vr:height>2160</vr:height></vr:qualityLevel>"))
        assertTrue(xml, xml.contains("<vr:latency>120</vr:latency><vr:accuracy>10</vr:accuracy><vr:cause>0</vr:cause><vr:cause>2</vr:cause>"))
        val rendered = mapper.writeValueAsString(VrMetric(renderedViewports = arrayListOf(RenderedViewportEntry("PT2S", 1500, viewport))))
        assertTrue(rendered, rendered.startsWith("<VrMetric><vr:renderedViewports><vr:startTime>PT2S</vr:startTime><vr:duration>1500</vr:duration><vr:viewport>"))
    }

    // clause 9.4.2: the extension is announced by xsi:type; a report without VR metrics is as before
    @Test
    fun aReportWithoutVrMetricsHasNoExtension() {
        val xml = mapper.writeValueAsString(QoeReport(periodId = "P1", reportTime = "t", reportPeriod = 5, recordingSessionId = "ab"))
        assertFalse(xml, xml.contains("vr:"))
        assertFalse(xml, xml.contains("xsi:type"))
        val vr = mapper.writeValueAsString(
            QoeReport(periodId = "P1", reportTime = "t", reportPeriod = 5, recordingSessionId = "ab", vrMetricSchemaVersion = 1, xsiType = "vr:VrQoeReportType")
        )
        assertTrue(vr, vr.contains("xsi:type=\"vr:VrQoeReportType\""))
        assertTrue(vr, vr.contains("<vr:vrMetricSchemaVersion>1</vr:vrMetricSchemaVersion>"))
    }

    // Implementation choice, text and schema conflicting, see CR-052 (O-43): table 9.3.2-1 allows negative angles, so the type holds them and the
    // report writes them as they are (the schema of clause 9.4.3 would reject them; the text wins)
    @Test
    fun theAnglesOfAViewportAreSignedAsTable932_1Allows() {
        val viewport = ViewportData(-180L * 65536, -90L * 65536, -180L * 65536, 6553600, 3932160)
        val xml = mapper.writeValueAsString(RenderedViewportEntry("PT2S", 1500, viewport))
        assertTrue(xml, xml.contains(">-11796480</vr:centreAzimuth>"))
        assertTrue(xml, xml.contains(">-5898240</vr:centreElevation>"))
        assertTrue(xml, xml.contains(">-11796480</vr:centreTilt>"))
    }

    // Implementation choice, text silent, see CR-034 (O-46): the CMCD classes are those of version 1, the edition that TS 26.512 cites
    @Test
    fun theCmcdClassesAreThoseOfVersion1() {
        assertTrue("pr" in CmcdSchemes.KEYS.getValue(CmcdSchemes.SESSION))
        assertTrue("nrr" in CmcdSchemes.KEYS.getValue(CmcdSchemes.REQUEST))
        assertFalse("pr" in CmcdSchemes.KEYS.getValue(CmcdSchemes.STATUS))
    }
}
