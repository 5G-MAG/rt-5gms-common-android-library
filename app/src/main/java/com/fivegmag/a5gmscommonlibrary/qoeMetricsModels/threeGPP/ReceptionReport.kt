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

@JacksonXmlRootElement(localName = "ReceptionReport")
data class ReceptionReport(
    @field:JacksonXmlElementWrapper(useWrapping = false)
    @field:JacksonXmlProperty(localName = "QoeReport")
    var qoeReport: QoeReport,

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
)


