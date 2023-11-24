/*
License: 5G-MAG Public License (v1.0)
Author: Daniel Silhavy
Copyright: (C) 2023 Fraunhofer FOKUS
For full license terms please see the LICENSE file distributed with this
program. If this file is missing then the license can be retrieved from
https://drive.google.com/file/d/1cinCiA778IErENZ3JN52VFW-1ffHpx7Z/view
*/

package com.fivegmag.a5gmscommonlibrary.consumptionReporting

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonFilter
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fivegmag.a5gmscommonlibrary.models.EndpointAddress
import com.fivegmag.a5gmscommonlibrary.models.TypedLocation
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
data class ConsumptionReport(
    val mediaPlayerEntry: String,
    val reportingClientId: String,
    val consumptionReportingUnits: ArrayList<ConsumptionReportingUnit>
) : Parcelable

@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFilter("consumptionReportingUnitFilter")
data class ConsumptionReportingUnit(
    val mediaConsumed: String,
    var mediaEndpointAddress: EndpointAddress? = null,
    val startTime: String,
    var duration: Int,
    var locations: ArrayList<TypedLocation>? = ArrayList(),
    @JsonIgnore
    var mimeType: String? = null,
    @JsonIgnore
    var finished: Boolean = false
) : Parcelable

