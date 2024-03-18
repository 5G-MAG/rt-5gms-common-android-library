/*
License: 5G-MAG Public License (v1.0)
Author: Daniel Silhavy
Copyright: (C) 2023 Fraunhofer FOKUS
For full license terms please see the LICENSE file distributed with this
program. If this file is missing then the license can be retrieved from
https://drive.google.com/file/d/1cinCiA778IErENZ3JN52VFW-1ffHpx7Z/view
*/

package com.fivegmag.a5gmscommonlibrary.qoeMetricsReporting

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
