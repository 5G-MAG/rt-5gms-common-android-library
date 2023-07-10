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

data class RepresentationSwitchList(
    @field:JacksonXmlProperty(localName = "RepSwitchEvent")
    @field:JacksonXmlElementWrapper(useWrapping = false)
    val entries: ArrayList<RepresentationSwitch>
)

data class RepresentationSwitch(
    @field:JacksonXmlProperty(isAttribute = true)
    val t: String? = null,
    @field:JacksonXmlProperty(isAttribute = true)
    val mt: Long? = null,
    @field:JacksonXmlProperty(isAttribute = true)
    val to: String = "",
    @field:JacksonXmlProperty(isAttribute = true)
    val lto: Int? = null
)