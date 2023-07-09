package com.fivegmag.a5gmscommonlibrary.qoeMetricsModels.threeGPP

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class BufferLevel(
    @field:JacksonXmlProperty(localName = "BufferLevelEntry")
    @field:JacksonXmlElementWrapper(useWrapping = false)
    val entries: ArrayList<BufferLevelEntry>
)

data class BufferLevelEntry(
    @field:JacksonXmlProperty(isAttribute = true)
    val t: Long = 0,
    @field:JacksonXmlProperty(isAttribute = true)
    val level: Int = 0
)
