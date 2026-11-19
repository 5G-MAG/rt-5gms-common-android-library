/*
License: 5G-MAG Public License (v1.0)
Author: Daniel Silhavy
Copyright: (C) 2023 Fraunhofer FOKUS
For full license terms please see the LICENSE file distributed with this
program. If this file is missing then the license can be retrieved from
https://drive.google.com/file/d/1cinCiA778IErENZ3JN52VFW-1ffHpx7Z/view
*/

package com.fivegmag.a5gmscommonlibrary.qoeMetricsReporting

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "ReceptionReport")
@JsonPropertyOrder("schemaVersion", "qoeReport", "delimiter")
data class ReceptionReport(
    @field:JacksonXmlProperty(localName = "sv:schemaVersion")
    var schemaVersion: String? = null,

    @field:JacksonXmlElementWrapper(useWrapping = false)
    @field:JacksonXmlProperty(localName = "QoeReport")
    var qoeReport: QoeReport,

    @field:JacksonXmlProperty(localName = "sv:delimiter")
    var delimiter: Delimiter? = null,

    @field:JacksonXmlProperty(isAttribute = true, localName = "contentURI")
    var contentUri: String,

    @field:JacksonXmlProperty(isAttribute = true, localName = "clientID")
    var clientId: String? = null,

    @field:JacksonXmlProperty(isAttribute = true, localName = "xmlns")
    var xmlns: String? = null,

    @field:JacksonXmlProperty(isAttribute = true, localName = "xsi:schemaLocation")
    var schemaLocation: String? = null,

    @field:JacksonXmlProperty(isAttribute = true, localName = "xmlns:xsi")
    var xsi: String? = null,

    @field:JacksonXmlProperty(isAttribute = true, localName = "xmlns:sv")
    var sv: String? = null,

    @field:JacksonXmlProperty(isAttribute = true, localName = "xmlns:sup")
    var sup: String? = null,

    // the name space of the virtual reality metrics (TS 26.118 V19.0.0 clause 9.4.3), declared when the report carries them
    @field:JsonInclude(JsonInclude.Include.NON_NULL)
    @field:JacksonXmlProperty(isAttribute = true, localName = "xmlns:vr")
    var vr: String? = null,
)