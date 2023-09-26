/*
License: 5G-MAG Public License (v1.0)
Author: Daniel Silhavy
Copyright: (C) 2023 Fraunhofer FOKUS
For full license terms please see the LICENSE file distributed with this
program. If this file is missing then the license can be retrieved from
https://drive.google.com/file/d/1cinCiA778IErENZ3JN52VFW-1ffHpx7Z/view
*/

package com.fivegmag.a5gmscommonlibrary.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ServiceAccessInformation(
    val provisioningSessionId : String,
    val provisioningSessionType: String?,
    val streamingAccess: StreamingAccess,
    var clientConsumptionReportingConfiguration: ClientConsumptionReportingConfiguration
) : Parcelable

@Parcelize
data class StreamingAccess(
    val entryPoints: ArrayList<EntryPoint>?
) : Parcelable

@Parcelize
data class ClientConsumptionReportingConfiguration(
    val serverAddresses : Array<String>,
    val locationReporting: Boolean,
    val samplePercentage: Float,
    val reportingInterval: UInt,
    val accessReporting: Boolean
) : Parcelable