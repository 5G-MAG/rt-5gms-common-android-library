package com.fivegmag.a5gmscommonlibrary.qoeMetricsReporting

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

/**
 * The virtual reality metrics of a QoE report: the types of the schema of TS 26.118 V19.0.0 clause 9.4.3 (name space urn:3gpp:metadata:2020:VR:metrics,
 * prefix vr), which the report declares. All elements are qualified. The values of the tables of clause 9.3 are given in the units of the tables.
 */
@JsonPropertyOrder("compQualLatency", "renderedViewports", "vrDeviceInformation")
data class VrMetric(
    @field:JsonInclude(JsonInclude.Include.NON_NULL)
    @field:JacksonXmlElementWrapper(useWrapping = false)
    @field:JacksonXmlProperty(localName = "vr:compQualLatency")
    var compQualLatency: ArrayList<CompQualLatencyEntry>? = null,

    @field:JsonInclude(JsonInclude.Include.NON_NULL)
    @field:JacksonXmlElementWrapper(useWrapping = false)
    @field:JacksonXmlProperty(localName = "vr:renderedViewports")
    var renderedViewports: ArrayList<RenderedViewportEntry>? = null,

    @field:JsonInclude(JsonInclude.Include.NON_NULL)
    @field:JacksonXmlElementWrapper(useWrapping = false)
    @field:JacksonXmlProperty(localName = "vr:vrDeviceInformation")
    var vrDeviceInformation: ArrayList<VrDeviceInformationEntry>? = null
)

/** Table 9.3.2-3 (CompQualLatency, Entry); cause is the list of the codes of the causes */
@JsonPropertyOrder("firstViewport", "secondViewport", "worstViewport", "time", "mtime", "latency", "accuracy", "cause")
data class CompQualLatencyEntry(
    @field:JacksonXmlProperty(localName = "vr:firstViewport") val firstViewport: ViewportItem,
    @field:JacksonXmlProperty(localName = "vr:secondViewport") val secondViewport: ViewportItem,
    @field:JacksonXmlProperty(localName = "vr:worstViewport") val worstViewport: ViewportItem,
    @field:JacksonXmlProperty(localName = "vr:time") val time: String,
    @field:JacksonXmlProperty(localName = "vr:mtime") val mtime: String,
    @field:JacksonXmlProperty(localName = "vr:latency") val latency: Long,
    @field:JacksonXmlProperty(localName = "vr:accuracy") val accuracy: Long,
    @field:JsonInclude(JsonInclude.Include.NON_NULL)
    @field:JacksonXmlElementWrapper(useWrapping = false)
    @field:JacksonXmlProperty(localName = "vr:cause")
    val cause: ArrayList<Int>? = null
)

/** Table 9.3.3-1 (RenderedViewports, Entry) */
@JsonPropertyOrder("startTime", "duration", "viewport")
data class RenderedViewportEntry(
    @field:JacksonXmlProperty(localName = "vr:startTime") val startTime: String,
    @field:JacksonXmlProperty(localName = "vr:duration") val duration: Long,
    @field:JacksonXmlProperty(localName = "vr:viewport") val viewport: ViewportData
)

/** Table 9.3.4-1 (VrDeviceInformation, Entry); a value that cannot be logged is 0 or the empty string (clause 9.3.4) */
@JsonPropertyOrder(
    "start", "mstart", "deviceIdentifier", "horizontalResolution", "verticalResolution", "horizontalFoV", "verticalFoV",
    "renderedHorizontalFoV", "renderedVerticalFoV", "refreshRate"
)
data class VrDeviceInformationEntry(
    @field:JacksonXmlProperty(localName = "vr:start") val start: String,
    @field:JacksonXmlProperty(localName = "vr:mstart") val mstart: String,
    @field:JacksonXmlProperty(localName = "vr:deviceIdentifier") val deviceIdentifier: String,
    @field:JacksonXmlProperty(localName = "vr:horizontalResolution") val horizontalResolution: Long,
    @field:JacksonXmlProperty(localName = "vr:verticalResolution") val verticalResolution: Long,
    @field:JacksonXmlProperty(localName = "vr:horizontalFoV") val horizontalFoV: Long,
    @field:JacksonXmlProperty(localName = "vr:verticalFoV") val verticalFoV: Long,
    @field:JacksonXmlProperty(localName = "vr:renderedHorizontalFoV") val renderedHorizontalFoV: Long,
    @field:JacksonXmlProperty(localName = "vr:renderedVerticalFoV") val renderedVerticalFoV: Long,
    @field:JacksonXmlProperty(localName = "vr:refreshRate") val refreshRate: Long
)

/** Table 9.3.2-2 (ViewportItem): the viewport and the quality levels of the regions in it */
@JsonPropertyOrder("position", "qualityLevel")
data class ViewportItem(
    @field:JacksonXmlProperty(localName = "vr:position") val position: ViewportData,
    @field:JacksonXmlElementWrapper(useWrapping = false)
    @field:JacksonXmlProperty(localName = "vr:qualityLevel") val qualityLevel: ArrayList<QualityLevelEntry>
)

/** The region of a viewport (QualityLevels entry of table 9.3.2-2): coverage in percent, quality ranking and resolution of the region */
@JsonPropertyOrder("coverage", "qr", "width", "height")
data class QualityLevelEntry(
    @field:JacksonXmlProperty(localName = "vr:coverage") val coverage: Double,
    @field:JacksonXmlProperty(localName = "vr:qr") val qr: Long,
    @field:JacksonXmlProperty(localName = "vr:width") val width: Long,
    @field:JacksonXmlProperty(localName = "vr:height") val height: Long
)

/**
 * Table 9.3.2-1 (ViewportDataType): the angles are in units of 2^-16 degrees. The angles are signed: the table allows a centre azimuth and tilt from
 * -180 * 2^16 and a centre elevation from -90 * 2^16. Implementation choice, text and schema conflicting, see CR-052 (open item O-43): the schema of
 * clause 9.4.3 types them xs:unsignedInt, which no negative value can have; the text of the table wins.
 */
@JsonPropertyOrder("centreAzimuth", "centreElevation", "centreTilt", "azimuthRange", "elevationRange")
data class ViewportData(
    @field:JacksonXmlProperty(localName = "vr:centreAzimuth") val centreAzimuth: Long,
    @field:JacksonXmlProperty(localName = "vr:centreElevation") val centreElevation: Long,
    @field:JacksonXmlProperty(localName = "vr:centreTilt") val centreTilt: Long,
    @field:JacksonXmlProperty(localName = "vr:azimuthRange") val azimuthRange: Long,
    @field:JacksonXmlProperty(localName = "vr:elevationRange") val elevationRange: Long
)
