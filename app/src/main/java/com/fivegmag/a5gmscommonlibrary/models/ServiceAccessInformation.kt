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
    var clientConsumptionReportingConfiguration: ClientConsumptionReportingConfiguration?,
    var clientMetricsReportingConfigurations: ArrayList<ClientMetricsReportingConfiguration>?
) : Parcelable

@Parcelize
data class StreamingAccess(
    val entryPoints: ArrayList<EntryPoint>?
) : Parcelable

@Parcelize
data class ClientConsumptionReportingConfiguration(
    val serverAddresses : ArrayList<String>,
    val locationReporting: Boolean,
    val samplePercentage: Float,
    val reportingInterval: Int? = null,
    val accessReporting: Boolean
) : Parcelable

@Parcelize
data class ClientMetricsReportingConfiguration(
    val metricsReportingConfigurationId: String,
    val serverAddresses: ArrayList<String>,
    val scheme: String,
    val dataNetworkName: String?,
    val reportingInterval: Long? = null,
    val samplePercentage: Float? = null,
    val urlFilters: ArrayList<String>?,
    val metrics: ArrayList<String>,

    // These are additional fields that we need for processing. They are not included in the spec and are populated during runtime
    var isSchemeSupported: Boolean? = false
) : Parcelable