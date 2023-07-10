/*
License: 5G-MAG Public License (v1.0)
Author: Daniel Silhavy
Copyright: (C) 2023 Fraunhofer FOKUS
For full license terms please see the LICENSE file distributed with this
program. If this file is missing then the license can be retrieved from
https://drive.google.com/file/d/1cinCiA778IErENZ3JN52VFW-1ffHpx7Z/view
*/

package com.fivegmag.a5gmscommonlibrary.qoeMetricsModels.threeGPP

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class HttpList(
    @field:JacksonXmlElementWrapper(useWrapping = false)
    @field:JacksonXmlProperty(localName = "HttpListEntry")
    val entries: ArrayList<HttpListEntry>
)

data class HttpListEntry(
    @field:JacksonXmlProperty(isAttribute = true)
    val tcpid: Int = -1,
    @field:JacksonXmlProperty(isAttribute = true)
    val type: String = HttpListEntryType.OTHER.value,
    @field:JacksonXmlProperty(isAttribute = true)
    val url: String = "",
    @field:JacksonXmlProperty(isAttribute = true)
    val actualurl: String = "",
    @field:JacksonXmlProperty(isAttribute = true)
    val range: String = "",
    @field:JacksonXmlProperty(isAttribute = true)
    val trequest: String = "",
    @field:JacksonXmlProperty(isAttribute = true)
    val tresponse: String = "",
    @field:JacksonXmlProperty(isAttribute = true)
    val responsecode: Int = -1,
    @field:JacksonXmlProperty(isAttribute = true)
    val interval: Int = -1,
    @field:JacksonXmlProperty(localName = "Trace")
    @field:JacksonXmlElementWrapper(useWrapping = false)
    val traces: ArrayList<Trace>
)
data class Trace(
    @field:JacksonXmlProperty(isAttribute = true)
    val s: String = "",
    @field:JacksonXmlProperty(isAttribute = true)
    val d: Long = -1,
    @field:JacksonXmlProperty(isAttribute = true)
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