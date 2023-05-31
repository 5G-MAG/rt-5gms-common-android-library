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
    val clientMetricsReportingConfigurations: ArrayList<ClientMetricReportingConfiguration>?
) : Parcelable

@Parcelize
data class StreamingAccess(
    val entryPoints: ArrayList<EntryPoint>?
) : Parcelable

@Parcelize
data class ClientMetricReportingConfiguration(
    val metricsReportingConfigurationId: String,
    val serverAddresses: ArrayList<String>,
    val scheme: String,
    val dataNetworkName: String?,
    val reportingInterval: Int?,
    val samplePercentage: Float?,
    val urlFilters: ArrayList<String>?,
    val metrics: ArrayList<String>
) : Parcelable
