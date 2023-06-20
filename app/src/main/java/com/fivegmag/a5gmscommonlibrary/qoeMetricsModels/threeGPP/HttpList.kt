package com.fivegmag.a5gmscommonlibrary.qoeMetricsModels.threeGPP

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

data class HttpList(
    @field:ElementList(name = "HttpList") val entries: ArrayList<HttpListEntry>
)

@Root(name = "HttpListEntry")
data class HttpListEntry(
    @field:Attribute(name = "tcpid")
    @param:Attribute(name = "tcpid")
    val tcpid: Int = -1,

    @field:Attribute(name = "type")
    @param:Attribute(name = "type")
    val type: String = HttpListEntryType.OTHER.value,

    @field:Attribute(name = "url")
    @param:Attribute(name = "url")
    val url: String = "",

    @field:Attribute(name = "actualurl")
    @param:Attribute(name = "actualurl")
    val actualurl: String = "",

    @field:Attribute(name = "range")
    @param:Attribute(name = "range")
    val range: String = "",

    @field:Attribute(name = "trequest")
    @param:Attribute(name = "trequest")
    val trequest: Long = -1,

    @field:Attribute(name = "tresponse")
    @param:Attribute(name = "tresponse")
    val tresponse: Long = -1,

    @field:Attribute(name = "responsecode")
    @param:Attribute(name = "responsecode")
    val responsecode: Int = -1,

    @field:Attribute(name = "interval")
    @param:Attribute(name = "interval")
    val interval: Int = -1,

    @field:ElementList(name = "Trace") val entries: ArrayList<Trace>
)

@Root(name = "TraceEntry")
data class Trace(
    @field:Attribute(name = "s")
    @param:Attribute(name = "s")
    val s: Long = -1,

    @field:Attribute(name = "d")
    @param:Attribute(name = "d")
    val d: Long = -1,

    @field:Attribute(name = "b")
    @param:Attribute(name = "b")
    val b: Int = -1,
)

enum class HttpListEntryType(val value : String) {
    MPD("MPD"),
    XLINK_EXPANSION("Xlink expansion"),
    INITIALIZATION_SEGMENT("Initialization Segment"),
    INDEX_SEGMENT("Index Segment"),
    MEDIA_SEGMENT("Media Segment"),
    BITSTREAM_SWITCHING_SEGMENT("Bitstream Switching Segment"),
    REMOTE_XLINK_ELEMENT("Remote xlink element"),
    MPD_PATCH("MPD patch"),
    OTHER("other")
}