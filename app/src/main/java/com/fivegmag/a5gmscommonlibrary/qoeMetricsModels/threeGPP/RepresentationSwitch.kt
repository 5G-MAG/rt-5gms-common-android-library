package com.fivegmag.a5gmscommonlibrary.qoeMetricsModels.threeGPP

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class RepresentationSwitchList(
    @field:JacksonXmlProperty(localName = "RepSwitchEvent")
    @field:JacksonXmlElementWrapper(useWrapping = false)
    val entries: ArrayList<RepresentationSwitch>
)

data class RepresentationSwitch(
    @field:JacksonXmlProperty(isAttribute = true)
    val t: Long? = null,
    @field:JacksonXmlProperty(isAttribute = true)
    val mt: Long? = null,
    @field:JacksonXmlProperty(isAttribute = true)
    val to: String = "",
    @field:JacksonXmlProperty(isAttribute = true)
    val lto: Int? = null
)