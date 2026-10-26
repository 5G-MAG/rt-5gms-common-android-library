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
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ServiceAccessInformation(
    val provisioningSessionId : String,
    val provisioningSessionType: String?,
    // 0..1 in table 9.2.3.1-1 of TS 26.510 V19.2.0: a Provisioning Session that offers no streaming access has none
    val streamingAccess: StreamingAccess? = null,
    var clientConsumptionReportingConfiguration: ClientConsumptionReportingConfiguration?,
    var clientMetricsReportingConfigurations: ArrayList<ClientMetricsReportingConfiguration>?,
    // TS 26.510 V19.2.0 table 9.2.3.1-1 (1..1): false when the resource omits it; it governs the location data of consumption reporting too
    // (the Client Consumption Reporting Configuration has no such property)
    val locationReporting: Boolean = false,
    // the properties below are absent from the resource of a Media AF that does not offer them
    // table 9.2.3.1-1 names the property notificationURL; a Media AF generated from the YAML of TS 26.510 spells it notficationURL
    @SerializedName("notificationURL", alternate = ["notficationURL"])
    val notificationURL: String? = null,
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
    // TS 26.510 V19.2.0 table 9.5.3.1-1: the locations where metrics are collected (LocationArea5G of TS 29.122 V19.7.0 clause 5.2.1.2.17)
    val locationFilter: ArrayList<LocationArea5G>? = null,

    // These are additional fields that we need for processing. They are not included in the spec and are populated during runtime
    var isSchemeSupported: Boolean? = false
) : Parcelable
/** LocationArea5G (TS 29.122 V19.7.0 clause 5.2.1.2.17): the network area, the geographic areas and the civic addresses */
@Parcelize
data class LocationArea5G(
    val nwAreaInfo: NetworkAreaInfo? = null,
    val geographicAreas: ArrayList<GeographicArea>? = null,
    val civicAddresses: ArrayList<CivicAddress>? = null
) : Parcelable

/**
 * CivicAddress (TS 29.572 V19.7.0 clause 6.1.6.2.14, the elements of IETF RFC 4776): the country code and the elements that the Media Session
 * Handler can compare with an address of the UE (A1 to A4, road, house number, postal code); an area that names any other element is not
 * matched (the UE cannot show it)
 */
@Parcelize
data class CivicAddress(
    val country: String? = null,
    @SerializedName("A1") val a1: String? = null,
    @SerializedName("A2") val a2: String? = null,
    @SerializedName("A3") val a3: String? = null,
    @SerializedName("A4") val a4: String? = null,
    @SerializedName("A5") val a5: String? = null,
    @SerializedName("A6") val a6: String? = null,
    @SerializedName("RD") val rd: String? = null,
    @SerializedName("HNO") val hno: String? = null,
    @SerializedName("PC") val pc: String? = null,
    @SerializedName("PRD") val prd: String? = null,
    @SerializedName("POD") val pod: String? = null,
    @SerializedName("STS") val sts: String? = null,
    @SerializedName("HNS") val hns: String? = null,
    @SerializedName("LMK") val lmk: String? = null,
    @SerializedName("LOC") val loc: String? = null,
    @SerializedName("NAM") val nam: String? = null,
    @SerializedName("BLD") val bld: String? = null,
    @SerializedName("UNIT") val unit: String? = null,
    @SerializedName("FLR") val flr: String? = null,
    @SerializedName("ROOM") val room: String? = null,
    @SerializedName("PLC") val plc: String? = null,
    @SerializedName("PCN") val pcn: String? = null,
    @SerializedName("POBOX") val pobox: String? = null,
    @SerializedName("ADDCODE") val addcode: String? = null,
    @SerializedName("SEAT") val seat: String? = null,
    @SerializedName("RDSEC") val rdsec: String? = null,
    @SerializedName("RDBR") val rdbr: String? = null,
    @SerializedName("RDSUBBR") val rdsubbr: String? = null,
    @SerializedName("PRM") val prm: String? = null,
    @SerializedName("POM") val pom: String? = null
) : Parcelable

/**
 * GeographicArea (TS 29.572 V19.7.0 clause 6.1.6.2.5), one class for all the shapes: "shape" names it (POINT, POINT_UNCERTAINTY_CIRCLE,
 * POINT_UNCERTAINTY_ELLIPSE, POLYGON, POINT_ALTITUDE, POINT_ALTITUDE_UNCERTAINTY, ELLIPSOID_ARC) and decides which of the others are present
 */
@Parcelize
data class GeographicArea(
    val shape: String,
    val point: GeographicalCoordinates? = null,
    val uncertainty: Double? = null,
    val uncertaintyEllipse: UncertaintyEllipse? = null,
    val pointList: ArrayList<GeographicalCoordinates>? = null,
    val innerRadius: Double? = null,
    val uncertaintyRadius: Double? = null,
    val offsetAngle: Double? = null,
    val includedAngle: Double? = null
) : Parcelable

@Parcelize
data class GeographicalCoordinates(val lon: Double, val lat: Double) : Parcelable

@Parcelize
data class UncertaintyEllipse(val semiMajor: Double, val semiMinor: Double, val orientationMajor: Double) : Parcelable

/** NetworkAreaInfo (TS 29.554 V19.2.1 clause 5.6.2.8): cells and tracking areas; the NG-RAN node identities are not modelled */
@Parcelize
data class NetworkAreaInfo(
    val ecgis: ArrayList<Ecgi>? = null,
    val ncgis: ArrayList<Ncgi>? = null,
    val tais: ArrayList<Tai>? = null
) : Parcelable

@Parcelize
data class PlmnId(val mcc: String, val mnc: String) : Parcelable

@Parcelize
data class Tai(val plmnId: PlmnId, val tac: String) : Parcelable

@Parcelize
data class Ecgi(val plmnId: PlmnId, val eutraCellId: String) : Parcelable

@Parcelize
data class Ncgi(val plmnId: PlmnId, val nrCellId: String) : Parcelable

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
    val qoSMonitoringEnablementPreference: Boolean = false,
    // table 9.2.3.1-1: the Background Data Transfer windows that this policy template offers (BdtWindow, clause 7.3.3.14)
    val bdtWindows: ArrayList<BdtWindow>? = null
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
