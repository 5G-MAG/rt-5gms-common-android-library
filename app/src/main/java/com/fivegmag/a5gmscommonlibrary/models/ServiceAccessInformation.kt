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
    var clientMetricsReportingConfigurations: ArrayList<ClientMetricsReportingConfiguration>?,
    // TS 26.510 V19.2.0 table 9.2.3.1-1; the properties below are absent from the resource of a Media AF that does not offer them
    val locationReporting: Boolean? = null,
    val notficationURL: String? = null, // the spelling of the property in TS26510_Maf_SessionHandling_ServiceAccessInformation.yaml
    val rtcClientConfiguration: RtcClientConfiguration? = null,
    val dynamicPolicyInvocationConfiguration: DynamicPolicyInvocationConfiguration? = null,
    val networkAssistanceConfiguration: NetworkAssistanceConfiguration? = null,
    val clientEdgeResourcesConfiguration: ClientEdgeResourcesConfiguration? = null
) : Parcelable

@Parcelize
data class StreamingAccess(
    val entryPoints: ArrayList<EntryPoint>?,
    val eMBMSServiceAnnouncementLocator: String? = null,
    val mbsExternalServiceIdentifier: String? = null
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
    val scheme: String?,
    val dataNetworkName: String?,
    val reportingInterval: Long? = null,
    val samplePercentage: Float? = null,
    val urlFilters: ArrayList<String>?,
    val samplingPeriod: Long,
    val metrics: ArrayList<String>,
    val sliceScope: ArrayList<Snssai>? = null,
    val reportingStartOffset: Long? = null,
    val reportingDuration: Long? = null,
    val positiveCrossingThresholds: HashMap<String, ArrayList<Float>>? = null,
    val negativeCrossingThresholds: HashMap<String, ArrayList<Float>>? = null,

    // These are additional fields that we need for processing. They are not included in the spec and are populated during runtime
    var isSchemeSupported: Boolean? = false
) : Parcelable
@Parcelize
data class Snssai(
    val sst: Int,
    val sd: String? = null
) : Parcelable

@Parcelize
data class ClientServiceEndpointCredentials(
    val username: String,
    val passphrase: String
) : Parcelable

/** EndpointAddress with the credentials of a ClientServiceEndpointAccessParameters */
@Parcelize
data class ClientServiceEndpointAccessParameters(
    val domainName: String? = null,
    val portNumbers: List<Int>? = null,
    val credentials: ClientServiceEndpointCredentials? = null
) : Parcelable

@Parcelize
data class RtcClientConfiguration(
    val stunEndpoints: ArrayList<ClientServiceEndpointAccessParameters>? = null,
    val turnEndpoints: ArrayList<ClientServiceEndpointAccessParameters>? = null,
    val swapEndpoints: ArrayList<ClientServiceEndpointAccessParameters>? = null
) : Parcelable

@Parcelize
data class PolicyTemplateBinding(
    val externalReference: String,
    val policyTemplateId: String,
    val pduSetMarking: Boolean = false,
    val downlinkDataBurstSizeMarkingRequired: Boolean = false,
    val downlinkTimeToNextBurstMarkingRequired: Boolean = false,
    val downlinkExpeditedTransferIndicationMarkingRequired: Boolean = false,
    val l4SEnablementPreference: Boolean = false,
    val qoSMonitoringEnablementPreference: Boolean = false
    // bdtWindows (TS 26.510 V19.2.0 BdtWindow) are not modelled
) : Parcelable

@Parcelize
data class DynamicPolicyInvocationConfiguration(
    val serverAddresses: ArrayList<String>,
    val policyTemplateBindings: ArrayList<PolicyTemplateBinding>,
    val sdfMethods: ArrayList<String>
) : Parcelable

@Parcelize
data class NetworkAssistanceConfiguration(
    val serverAddresses: ArrayList<String>
) : Parcelable

@Parcelize
data class EasDiscoveryTemplate(
    val easId: String? = null,
    val easType: String? = null,
    val easProviderIds: ArrayList<String>? = null,
    val easFeatures: ArrayList<String>? = null
) : Parcelable

@Parcelize
data class ClientEasRelocationRequirements(
    val tolerance: String,
    val maxInterruptionDuration: Int? = null
) : Parcelable

@Parcelize
data class ClientEdgeResourcesConfiguration(
    val easDiscoveryTemplate: EasDiscoveryTemplate,
    val easRelocationRequirements: ClientEasRelocationRequirements? = null
    // eligibilityCriteria (EdgeProcessingEligibilityCriteria) is not modelled
) : Parcelable
