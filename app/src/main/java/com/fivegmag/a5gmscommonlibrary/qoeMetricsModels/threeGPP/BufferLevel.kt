package com.fivegmag.a5gmscommonlibrary.qoeMetricsModels.threeGPP

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList

data class BufferLevel(
    @field:ElementList(name = "BufferLevel") val entries: List<BufferLevelEntry>
)

@Root(name="BufferLevelEntry")
data class BufferLevelEntry(
    @field:Attribute(name = "t")
    @param:Attribute(name = "t")
    val t: Long = 0,

    @field:Attribute(name = "level")
    @param:Attribute(name = "level")
    val level: Int = 0
)
