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
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

data class QoeReport(
    @field:JacksonXmlElementWrapper(localName = "QoeMetric")
    @field:JacksonXmlProperty(localName = "HttpList")
    var httpList: ArrayList<HttpList>? = null,

    @field:JacksonXmlElementWrapper(localName = "QoeMetric")
    @field:JacksonXmlProperty(localName = "RepSwitchList")
    var representationSwitchList: ArrayList<RepresentationSwitchList>? = null,

    @field:JacksonXmlElementWrapper(localName = "QoeMetric")
    @field:JacksonXmlProperty(localName = "BufferLevel")
    var bufferLevel: ArrayList<BufferLevel>? = null,

    @field:JacksonXmlProperty(isAttribute = true, localName = "periodID")
    val periodId: Int = -1,

    @field:JacksonXmlProperty(isAttribute = true)
    val reportTime: Int = -1,

    @field:JacksonXmlProperty(isAttribute = true)
    val reportPeriod: Int = -1,
)


