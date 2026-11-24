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

data class AvgThroughput(
    @field:JacksonXmlProperty(isAttribute = true)
    val numBytes: Long,
    @field:JacksonXmlProperty(isAttribute = true)
    val activityTime: Long,
    @field:JacksonXmlProperty(isAttribute = true)
    val t: String,
    @field:JacksonXmlProperty(isAttribute = true)
    val duration: Long,
    @field:JacksonXmlProperty(isAttribute = true)
    val accessBearer: String? = null,
    @field:JacksonXmlProperty(isAttribute = true)
    val inactivityType: InactivityType? = null
)

enum class InactivityType {
    USER_REQUEST,
    CLIENT_MEASURE,
    ERROR
}
