/*
License: 5G-MAG Public License (v1.0)
Author: Daniel Silhavy
Copyright: (C) 2023 Fraunhofer FOKUS
For full license terms please see the LICENSE file distributed with this
program. If this file is missing then the license can be retrieved from
https://drive.google.com/file/d/1cinCiA778IErENZ3JN52VFW-1ffHpx7Z/view
*/

package com.fivegmag.a5gmscommonlibrary.qoeMetricsReporting

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

@JsonPropertyOrder(
    "representationSwitchList",
    "bufferLevel",
    "mpdInformation",
    "initialPlayoutDelay",
    "playoutDelayForMediaStartup",
    "avgThroughputList",
    "playList",
    "supplementQoEMetric",
    "delimiters",
    "periodId",
    "reportTime",
    "reportPeriod",
    "recordingSessionId"
)
data class QoeReport(
    @field:JacksonXmlElementWrapper(localName = "QoeMetric")
    @field:JacksonXmlProperty(localName = "RepSwitchList")
    var representationSwitchList: ArrayList<RepresentationSwitchList>? = null,

    @field:JacksonXmlElementWrapper(localName = "QoeMetric")
    @field:JacksonXmlProperty(localName = "BufferLevel")
    var bufferLevel: ArrayList<BufferLevel>? = null,

    @field:JacksonXmlElementWrapper(localName = "QoeMetric")
    @field:JacksonXmlProperty(localName = "MPDInformation")
    var mpdInformation: ArrayList<MpdInformation>? = null,

    @field:JacksonXmlElementWrapper(localName = "QoeMetric")
    @field:JacksonXmlProperty(localName = "InitialPlayoutDelay")
    var initialPlayoutDelay: ArrayList<InitialPlayoutDelay>? = null,

    @field:JacksonXmlElementWrapper(localName = "QoeMetric")
    @field:JacksonXmlProperty(localName = "PlayoutDelayforMediaStartup")
    var playoutDelayForMediaStartup: ArrayList<PlayoutDelayforMediaStartup>? = null,

    @field:JacksonXmlElementWrapper(localName = "QoeMetric")
    @field:JacksonXmlProperty(localName = "AvgThroughput")
    var avgThroughputList: ArrayList<AvgThroughput>? = null,

    @field:JacksonXmlElementWrapper(localName = "QoeMetric")
    @field:JacksonXmlProperty(localName = "PlayList")
    var playList: ArrayList<PlayList>? = null,

    @field:JacksonXmlProperty(localName = "sup:supplementQoEMetric")
    @field:JacksonXmlElementWrapper(useWrapping = false)
    var supplementQoEMetric: ArrayList<SupplementQoeMetric>? = null,

    @field:JacksonXmlProperty(localName = "sv:delimiter")
    @field:JacksonXmlElementWrapper(useWrapping = false)
    var delimiters: ArrayList<Delimiter>? = null,

    @field:JacksonXmlProperty(isAttribute = true, localName = "periodID")
    var periodId: String = "",

    @field:JacksonXmlProperty(isAttribute = true)
    var reportTime: String = "",

    @field:JacksonXmlProperty(isAttribute = true)
    var reportPeriod: Int? = 0,

    @field:JacksonXmlProperty(isAttribute = true)
    var recordingSessionId: String? = ""
)
