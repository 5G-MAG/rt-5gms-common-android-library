package com.fivegmag.a5gmscommonlibrary.qoeMetricsModels.threeGPP

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class MpdInformation(
    @field:JacksonXmlProperty(isAttribute = true)
    val representationId: String? = "",
    @field:JacksonXmlProperty(isAttribute = true)
    val subrepLevel: Int? = null,
    @field:JacksonXmlProperty(localName = "Mpdinfo")
    val mpdInfo: MpdInfo
)

data class MpdInfo(
    @field:JacksonXmlProperty(isAttribute = true)
    val codecs: String? = "",
    @field:JacksonXmlProperty(isAttribute = true)
    val bandwidth: Int,
    @field:JacksonXmlProperty(isAttribute = true)
    val mimeType: String? = "",
    @field:JacksonXmlProperty(isAttribute = true)
    var qualityRanking: Int? = null,
    @field:JacksonXmlProperty(isAttribute = true)
    var frameRate: Double? = null,
    @field:JacksonXmlProperty(isAttribute = true)
    var width: Int? = null,
    @field:JacksonXmlProperty(isAttribute = true)
    var height: Int? = null,
)
