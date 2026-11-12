package com.fivegmag.a5gmscommonlibrary.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/** TimeWindow (TS 29.122 clause 5.2.1.2.9): the start and the stop date-time, as the Media AF writes them (RFC 3339) */
@Parcelize
data class TimeWindow(val startTime: String, val stopTime: String) : Parcelable

/** BdtWindow (TS 26.510 V19.2.0 table 7.3.3.14-1) */
@Parcelize
data class BdtWindow(
    val timeWindow: TimeWindow,
    val maximumDownlinkBitRate: String? = null,
    val maximumUplinkBitRate: String? = null
) : Parcelable

/** IpAddr (TS 29.571), the IPv4 form; the Media AF accepts IPv4 addresses in a 5_TUPLE filter */
@Parcelize
data class IpAddr(val ipv4Addr: String? = null, val ipv6Addr: String? = null) : Parcelable

/** IpPacketFilterSet (TS 26.510 V19.2.0 table 7.3.3.1-1), the properties that a 5-tuple uses */
@Parcelize
data class IpPacketFilterSet(
    val direction: String,
    val sourceAddress: IpAddr? = null,
    val destinationAddress: IpAddr? = null,
    val protocolNumber: Int? = null,
    val sourcePort: Int? = null,
    val destinationPort: Int? = null,
    // TS 26.510 V19.2.0 table 7.3.3.1-1
    val differentiatedServicesCodePoint: Int? = null,
    val flowLabel: Int? = null,
    val securityParametersIndex: Long? = null
) : Parcelable

/** ApplicationFlowDescription (table 7.3.3.2-1); exactly one of packetFilter and domainName is populated */
@Parcelize
data class ApplicationFlowDescription(
    val filterMethod: String,
    val packetFilter: IpPacketFilterSet? = null,
    val domainName: String? = null,
    val mediaType: String? = null
) : Parcelable

/** UnidirectionalBitRateSpecification (table 7.3.3.5-1); BitRate is the string of TS 29.571, for example "5 Mbps" */
@Parcelize
data class UnidirectionalBitRateSpecification(
    val maximumRequestedBitRate: String,
    val minimumDesiredBitRate: String,
    val minimumRequestedBitRate: String
) : Parcelable

/** ClientPolicySpecification (table 7.3.3.6-1). l4SRequired and qoSMonitoringRequired are declared so that a response carrying them is read. */
@Parcelize
data class ClientPolicySpecification(
    val downlinkBitRates: UnidirectionalBitRateSpecification,
    val uplinkBitRates: UnidirectionalBitRateSpecification,
    val l4SRequired: Boolean? = null,
    val qoSMonitoringRequired: Boolean? = null,
    // TS 26.510 V19.2.0 table 7.3.3.6-1: the desired packet latency (ms) and packet loss rate (tenths of a percent), and expedited transfer
    val desiredPacketLatency: Int? = null,
    val desiredPacketLossRate: Int? = null,
    val downlinkExpeditedTransferIndication: Boolean? = null
) : Parcelable

/** UsageThreshold (TS 29.122 clause 5.2.1.2.9), of which the volume in bytes is used */
@Parcelize
data class UsageThreshold(
    val duration: Int? = null,
    val totalVolume: Long? = null,
    val downlinkVolume: Long? = null,
    val uplinkVolume: Long? = null
) : Parcelable

/** ClientBdtSpecification (table 9.3.3.3-1) */
@Parcelize
data class ClientBdtSpecification(
    val windows: ArrayList<BdtWindow>,
    val estimatedDataTransferVolume: UsageThreshold? = null
) : Parcelable

/** ApplicationFlowBinding (table 9.3.3.2-1) */
@Parcelize
data class ApplicationFlowBinding(
    val componentReference: String,
    val applicationFlowDescription: ApplicationFlowDescription,
    val qosSpecification: ClientPolicySpecification? = null
) : Parcelable

/** QosMonitoringReport (TS 29.122 clause 5.4.2.7): the results that the Media AF passes on; the properties are kept as the Media AF sends them */
@Parcelize
data class QosMonitoringReport(
    val ulDelays: ArrayList<Int>? = null,
    val dlDelays: ArrayList<Int>? = null,
    val rtDelays: ArrayList<Int>? = null,
    val pdmf: Boolean? = null,
    val ulDataRate: String? = null,
    val dlDataRate: String? = null,
    val ulAggrDataRate: String? = null,
    val dlAggrDataRate: String? = null,
    val ulConInfo: Int? = null,
    val dlConInfo: Int? = null
) : Parcelable

/**
 * DynamicPolicy (TS 26.510 V19.2.0 table 9.3.3.1-1). dynamicPolicyId, provisioningSessionId, qosEnforcement, l4SEnabled, qoSMonitoringEnabled and
 * qoSMonitoringResults are read-only: the Media AF populates them, and a request leaves them out. sliceInfo is not modelled. location is the
 * location of the UE when the policy was created or last updated.
 */
@Parcelize
data class DynamicPolicy(
    val sessionId: String,
    val policyTemplateId: String,
    val applicationFlowBindings: ArrayList<ApplicationFlowBinding>,
    val dynamicPolicyId: String? = null,
    val provisioningSessionId: String? = null,
    val dataNetworkName: String? = null,
    val bdtSpecification: ClientBdtSpecification? = null,
    val qosEnforcement: Boolean? = null,
    val l4SEnabled: Boolean? = null,
    val qoSMonitoringEnabled: Boolean? = null,
    val qoSMonitoringResults: QosMonitoringReport? = null,
    val location: TypedLocation? = null
) : Parcelable

/**
 * The status currentDynamicPolicies of one media delivery session (table 11.3.2-1): the Service Operation Point of the policy, the
 * Background Data Transfer quota, the enablement of ECN marking for L4S and of QoS monitoring and the most recent QoS monitoring results
 */
@Parcelize
data class DynamicPolicyStatus(
    val sessionId: String,
    val serviceOperationPointReference: String,
    val dynamicPolicyId: String,
    val grantedBackgroundDataTransferWindows: ArrayList<BdtWindow>,
    val l4SEnabled: Boolean,
    val qoSMonitoringEnabled: Boolean,
    val qoSMonitoringResults: QosMonitoringReport?
) : Parcelable

/**
 * NetworkAssistanceSession (table 9.4.3.1-1). naSessionId, provisioningSessionId and recommendedQoS are read-only. mediaTransportParameters
 * are omitted in a Network Assistance Session (clause 5.3.4.2); sliceInfo and dataNetworkName are not modelled. location is the location of the
 * UE when the session was created or last updated.
 */
@Parcelize
data class NetworkAssistanceSession(
    val sessionId: String,
    val applicationFlowDescription: ApplicationFlowDescription,
    val naSessionId: String? = null,
    val provisioningSessionId: String? = null,
    val policyTemplateId: String? = null,
    val componentReference: String? = null,
    val requestedQoS: ClientPolicySpecification? = null,
    val recommendedQoS: ClientPolicySpecification? = null,
    val location: TypedLocation? = null
) : Parcelable

/** OperationSuccessResponse (table 7.3.3.9-1) */
data class OperationSuccessResponse(val success: Boolean, val reason: String? = null)
