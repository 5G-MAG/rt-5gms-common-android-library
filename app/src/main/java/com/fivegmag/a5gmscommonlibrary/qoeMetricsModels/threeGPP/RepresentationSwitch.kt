package com.fivegmag.a5gmscommonlibrary.qoeMetricsModels.threeGPP

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

data class RepresentationSwitchList(
    @field:ElementList(name = "RepSwitchList") val entries: ArrayList<RepresentationSwitch>
)

@Root(name = "RepSwitchEvent")
data class RepresentationSwitch(
    @field:Attribute(name = "t")
    @param:Attribute(name = "t")
    val t: Long = 0,

    @field:Attribute(name = "mt")
    @param:Attribute(name = "mt")
    val mt: Long = 0,

    @field:Attribute(name = "to")
    @param:Attribute(name = "to")
    val to: String = "",

    @field:Attribute(name = "lto")
    @param:Attribute(name = "lto")
    val lto: Int? = -1
)