package com.fivegmag.a5gmscommonlibrary.qoeMetricsModels.threeGPP

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "QoeReport")
data class QoeMetricsReport(
    @field:JacksonXmlElementWrapper(localName = "QoeMetric")
    @field:JacksonXmlProperty(localName = "HttpList")
    var httpList: ArrayList<HttpList>? = null,

    @field:JacksonXmlElementWrapper(localName = "QoeMetric")
    @field:JacksonXmlProperty(localName = "RepSwitchList")
    var representationSwitchList: ArrayList<RepresentationSwitchList>? = null,

    @field:JacksonXmlElementWrapper(localName = "QoeMetric")
    @field:JacksonXmlProperty(localName = "BufferLevel")
    var bufferLevel: ArrayList<BufferLevel>? = null

)


