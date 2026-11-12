/*
License: 5G-MAG Public License (v1.0)
Author: Daniel Silhavy
Copyright: (C) 2023 Fraunhofer FOKUS
For full license terms please see the LICENSE file distributed with this
program. If this file is missing then the license can be retrieved from
https://drive.google.com/file/d/1cinCiA778IErENZ3JN52VFW-1ffHpx7Z/view
*/

package com.fivegmag.a5gmscommonlibrary.helpers

object StatusInformation {
    const val AVERAGE_THROUGHPUT = "AverageThroughput"
    const val BUFFER_LENGTH = "BufferLength"
    const val LIVE_LATENCY = "liveLatency"
}

object PlayerStates {
    const val IDLE = "IDLE"
    const val BUFFERING = "BUFFERING"
    const val ENDED = "ENDED"
    const val READY = "READY"
    const val PLAYING = "PLAYING"
    const val PAUSED = "PAUSED"
    const val UNKNOWN = "UNKNOWN"
}

object SessionHandlerMessageTypes {
    const val STATUS_MESSAGE = 1
    const val START_PLAYBACK_BY_PROVISIONING_ID_MESSAGE = 2
    const val SERVICE_ACCESS_INFORMATION_MESSAGE = 3
    const val REGISTER_CLIENT = 4
    const val UNREGISTER_CLIENT = 5
    const val TRIGGER_PLAYBACK = 6
    const val UPDATE_LOOKUP_TABLE = 7
    const val SET_M5_ENDPOINT = 8
    const val START_PLAYBACK_BY_SERVICE_LIST_ENTRY_MESSAGE = 9
    const val GET_CONSUMPTION_REPORT = 10
    const val CONSUMPTION_REPORT = 11
    const val UPDATE_PLAYBACK_CONSUMPTION_REPORTING_CONFIGURATION = 12
    const val GET_QOE_METRICS_REPORT = 13
    const val REPORT_QOE_METRICS = 14

    // The general client API of TS 26.510 V19.2.0 clause 11.2.2: createMediaDeliverySession(), destroyMediaDeliverySession() and the
    // general status of table 11.2.3-1. The reply of a method is the message of the same name with _REPLY; a notification event of
    // tables 11.2.3-2 and 11.2.3-3 is a NOTIFICATION_EVENT message whose bundle names the event (SessionHandlerEvents).
    const val CREATE_MEDIA_DELIVERY_SESSION = 15
    const val CREATE_MEDIA_DELIVERY_SESSION_REPLY = 16
    const val DESTROY_MEDIA_DELIVERY_SESSION = 17
    const val GET_SESSION_STATUS = 18
    const val GET_SESSION_STATUS_REPLY = 19
    const val NOTIFICATION_EVENT = 20

    // The status of Consumption Reporting (table 11.5.2-1) and of Metrics Reporting (table 11.6.2-1) of a media delivery session
    const val GET_CONSUMPTION_REPORTING_STATUS = 21
    const val GET_CONSUMPTION_REPORTING_STATUS_REPLY = 22
    const val GET_METRICS_REPORTING_STATUS = 23
    const val GET_METRICS_REPORTING_STATUS_REPLY = 24

    // Step 10 of the DASH procedure of TS 26.501 V19.4.0 clause 5.2.3: the Media Player notifies the Media Session Handler about the MPD,
    // with parameters of it
    const val MANIFEST_NOTIFICATION = 25

    // The Media Stream Handler tells the Media Session Handler that a metric crossed one of the thresholds of a metrics reporting configuration
    // (TS 26.510 V19.2.0 clause 5.2.11.1); the bundle has the configuration identifier, the metric and the value
    const val METRIC_THRESHOLD_CROSSED = 26

    // The Dynamic Policy client API (TS 26.510 V19.2.0 clause 11.3) and the Network Assistance client API (clause 11.4): one message per
    // method, the reply is the message of the same name with _REPLY. GET_SERVICE_OPERATION_POINTS gives the application the Service Operation
    // Points (the policy template bindings of the Service Access Information) of a media delivery session.
    const val GET_SERVICE_OPERATION_POINTS = 27
    const val GET_SERVICE_OPERATION_POINTS_REPLY = 28
    const val GET_BDT_INFO = 29
    const val GET_BDT_INFO_REPLY = 30
    const val ACTIVATE_POLICY = 31
    const val ACTIVATE_POLICY_REPLY = 32
    const val DEACTIVATE_POLICY = 33
    const val DEACTIVATE_POLICY_REPLY = 34
    const val GET_DYNAMIC_POLICY_STATUS = 35
    const val GET_DYNAMIC_POLICY_STATUS_REPLY = 36
    const val REQUEST_BIT_RATE_RECOMMENDATION = 37
    const val REQUEST_BIT_RATE_RECOMMENDATION_REPLY = 38
    const val REQUEST_DELIVERY_BOOST = 39
    const val REQUEST_DELIVERY_BOOST_REPLY = 40

    // The Media Session Handler tells the Media Stream Handler whether the UE is inside the locationFilter of a CMCD metrics reporting
    // configuration (TS 26.510 V19.2.0 table 8.7.3.1-1); the bundle has the configuration identifier and LOCATED
    const val CMCD_LOCATION_UPDATE = 41

    // The implicit service launch (TS 26.510 V19.2.0 clause 5.4.2.2, clause 6): the application hands a 3GPP Service URL to the Media Session
    // Handler and gets the HTTP status code of TS 26.512 V19.3.0 clause 4.8.3.4 in HTTP_RESPONSE_CODE. Like any media delivery session of the
    // client it is served for the sender; the reply carries no media delivery session identifier (clause 5.4.2.2).
    const val LAUNCH_SERVICE_URL = 42
    const val LAUNCH_SERVICE_URL_REPLY = 43
}

// Table 11.2.3-1: sessionHandlingState
object SessionHandlingStates {
    const val ACTIVE = "ACTIVE"
    const val ERRORED = "ERRORED"
}

// The keys of the bundles of the general client API (tables 11.2.2.1-1, 11.2.2.1-2, 11.2.2.2-1, 11.2.3-1, 11.2.3-2)
object SessionHandlerBundleKeys {
    const val SERVICE_ID = "serviceId"
    const val CONTENT_ID = "contentId"
    const val ENTRY_POINT = "entryPoint"
    const val DOMAIN_NAMES = "domainNames"
    const val ACCESS_TOKEN = "accessToken"
    const val MEDIA_DELIVERY_SESSION_IDENTIFIER = "mediaDeliverySessionIdentifier"
    const val CONTENT_IDENTIFIER = "contentIdentifier"
    const val SESSION_HANDLING_STATE = "sessionHandlingState"
    const val EVENT = "event"
    const val MEDIA_ENTRY_POINT_URL = "mediaEntryPointUrl"
    const val STREAMING_ACCESS = "streamingAccess"

    // LAUNCH_SERVICE_URL: the 3GPP Service URL (TS 26.510 V19.2.0 clause 6.2)
    const val SERVICE_URL = "serviceUrl"

    // Every message of a Media Stream Handler instance carries the identifier of the instance: an application may run several
    // instances, one per media delivery session (TS 26.510 V19.2.0 clause 11.2.2.1 creates a new media delivery session on each call)
    const val CLIENT_INSTANCE_ID = "clientInstanceId"

    // The parameters of the MPD in MANIFEST_NOTIFICATION
    const val MPD_DYNAMIC = "mpdDynamic"
    const val MPD_DURATION_MS = "mpdDurationMs"
    const val MPD_MIN_BUFFER_TIME_MS = "mpdMinBufferTimeMs"
    const val MPD_PERIOD_COUNT = "mpdPeriodCount"

    // The parameters of METRIC_THRESHOLD_CROSSED
    const val THRESHOLD_METRIC = "thresholdMetric"
    const val THRESHOLD_VALUE = "thresholdValue"
    const val THRESHOLD_POSITIVE = "thresholdPositive"

    // Clauses 11.5 and 11.6: the reporting status and the payload of the reporting error events
    const val CONSUMPTION_REPORT = "consumptionReport"
    const val METRICS_REPORT = "metricsReport"
    const val PROVISIONING_SESSION_ID = "provisioningSessionId"
    const val METRICS_REPORTING_CONFIGURATION_ID = "metricsReportingConfigurationId"
    const val LOCATED = "located"
    const val SCHEME = "scheme"
    const val SERVER_ADDRESS = "serverAddress"
    const val HTTP_RESPONSE_CODE = "httpResponseCode"
    const val ERROR_MESSAGE = "errorMessage"

    // Clause 11.3: the input parameters (tables 11.3.1.1-1, 11.3.1.2-1, 11.3.1.3-1), the return values (tables 11.3.1.1-2, 11.3.1.2-2,
    // 11.3.1.3-2), the status (table 11.3.2-1) and the payloads of the events (tables 11.3.2-2, 11.3.2-3)
    const val SERVICE_OPERATION_POINT_REFERENCE = "serviceOperationPointReference"
    const val SERVICE_OPERATION_POINTS = "serviceOperationPoints"
    const val ESTIMATED_TRANSFER_VOLUME = "estimatedTransferVolume"
    const val SUCCEEDED = "succeeded"
    const val WINDOW_START = "windowStart"
    const val WINDOW_END = "windowEnd"
    const val MAXIMUM_DATA_TRANSFER_VOLUME = "maximumDataTransferVolume"
    const val RECOMMENDED_DOWNLINK_BIT_RATE = "recommendedDownlinkBitRate"
    const val RECOMMENDED_UPLINK_BIT_RATE = "recommendedUplinkBitRate"
    const val GRANTED_BACKGROUND_DATA_TRANSFER_WINDOWS = "grantedBackgroundDataTransferWindows"
    const val L4S_ENABLED = "l4SEnabled"
    const val QOS_MONITORING_ENABLED = "qoSMonitoringEnabled"
    const val QOS_MONITORING_RESULTS = "qoSMonitoringResults"
    const val CURRENT_DYNAMIC_POLICIES = "currentDynamicPolicies"
    const val ERROR_REASON = "errorReason"

    // Not an input parameter of table 11.3.1.2-1: the target QoS of the Service Operation Point that the Media Access Function determines
    // (TS 26.510 V19.2.0 clause 5.4.3), which the Media AF requires in the qosSpecification of a QoS-related Policy Template (clause 5.3.3.2, item 4)
    const val TARGET_DOWNLINK_BIT_RATE = "targetDownlinkBitRate"
    const val TARGET_UPLINK_BIT_RATE = "targetUplinkBitRate"

    // Clause 11.4: the input parameter componentReference (tables 11.4.1.1-1, 11.4.1.2-1) and the payloads of the events (tables 11.4.2-2, 11.4.2-3)
    const val COMPONENT_REFERENCE = "componentReference"
    const val RECOMMENDATION_SOURCE = "recommendationSource"
}

// The source of a bit rate recommendation (table 11.4.2-2): this Media Session Handler obtains it from the Media AF; the UE modem is the other source
object RecommendationSources {
    const val MEDIA_AF = "MEDIA_AF"
    const val UE_MODEM = "UE_MODEM"
}

object SessionHandlerEvents {
    object Notification {
        const val SESSION_HANDLING_ACTIVATED = "SESSION_HANDLING_ACTIVATED"
        const val SESSION_HANDLING_TERMINATED = "SESSION_HANDLING_TERMINATED"
        const val STREAMING_ACCESS_UPDATED = "STREAMING_ACCESS_UPDATED"

        // The former name of SESSION_HANDLING_TERMINATED, which no code sent
        @Deprecated("TS 26.510 V19.2.0 table 11.2.3-2 names the event SESSION_HANDLING_TERMINATED", ReplaceWith("SESSION_HANDLING_TERMINATED"))
        const val SESSION_HANDLING_STOPPED = SESSION_HANDLING_TERMINATED
    }

    // Tables 11.5.2-2 and 11.6.2-2
    object ConsumptionReporting {
        const val CONSUMPTION_REPORTING_ACTIVATED = "CONSUMPTION_REPORTING_ACTIVATED"
        const val CONSUMPTION_REPORTING_STOPPED = "CONSUMPTION_REPORTING_STOPPED"
        const val NEW_CONSUMPTION_REPORT = "NEW_CONSUMPTION_REPORT"
    }

    object MetricsReporting {
        const val METRICS_REPORTING_ACTIVATED = "METRICS_REPORTING_ACTIVATED"
        const val METRICS_REPORTING_STOPPED = "METRICS_REPORTING_STOPPED"
        const val NEW_METRICS_REPORT = "NEW_METRICS_REPORT"
    }

    // Table 11.3.2-2
    object DynamicPolicy {
        const val POLICY_ACTIVATED = "POLICY_ACTIVATED"
        const val POLICY_DEACTIVATED = "POLICY_DEACTIVATED"
        const val BACKGROUND_DATA_TRANSFER_OPPORTUNITY = "BACKGROUND_DATA_TRANSFER_OPPORTUNITY"
        const val L4S_ENABLED = "L4S_ENABLED"
        const val QOS_MONITORING_ENABLED = "QOS_MONITORING_ENABLED"
        const val QOS_MONITORING_RESULTS = "QOS_MONITORING_RESULTS"
    }

    // Table 11.4.2-2
    object NetworkAssistance {
        const val BIT_RATE_RECOMMENDATION = "BIT_RATE_RECOMMENDATION"
    }

    object Error {
        // Table 11.3.2-3
        const val ERROR_INVALID_SERVICE_OPERATION_POINT = "ERROR_INVALID_SERVICE_OPERATION_POINT"
        const val ERROR_UNAUTHORISED = "ERROR_UNAUTHORISED"
        const val ERROR_BACKGROUND_DATA_TRANSFER = "ERROR_BACKGROUND_DATA_TRANSFER"

        // Table 11.4.2-3
        const val ERROR_BIT_RATE_RECOMMENDATION = "ERROR_BIT_RATE_RECOMMENDATION"
        const val ERROR_DELIVERY_BOOST = "ERROR_DELIVERY_BOOST"

        const val ERROR_SESSION_HANDLING = "ERROR_SESSION_HANDLING"

        // Tables 11.5.2-3 and 11.6.2-3
        const val ERROR_CONSUMPTION_REPORTING = "ERROR_CONSUMPTION_REPORTING"
        const val ERROR_METRICS_REPORTING = "ERROR_METRICS_REPORTING"
    }
}

// TS 26.512 V19.3.0 tables 13.2.4-4 and 13.2.4-5: the content delivery protocols and the multipath preference of desiredContentDeliveryConfiguration
object DeliveryProtocols {
    const val HTTP_1_1 = "DELIVERY_PROTOCOL_HTTP_1_1"
    const val HTTP_2 = "DELIVERY_PROTOCOL_HTTP_2"
    const val HTTP_3 = "DELIVERY_PROTOCOL_HTTP_3"
}

object MultipathModes {
    const val PREFERRED = "MULTIPATH_PREFERRED"
    const val NOT_PREFERRED = "MULTIPATH_NOT_PREFERRED"
}

object ContentTypes {
    const val DASH = "application/dash+xml"
    const val HLS = "application/vnd.apple.mpegurl"
    const val JSON = "application/json"
    const val XML = "application/xml"
    // TS 26.247 clause 10.6.1 (MIME type of an XML-formatted QoE report)
    const val QOE_REPORT_XML = "application/3gpdash-qoe-report+xml"
}

/** TS 26.512 V19.3.0 clauses 6.2.3.2.2 and 6.2.3.2.3: the product token with the version of the specification the implementation complies with */
object UserAgentTokens {
    const val FIVE_G_MS_MEDIA_STREAM_HANDLER = "5GMSMediaStreamHandler/19.3.0"
    const val FIVE_G_MS_MEDIA_SESSION_HANDLER = "5GMSMediaSessionHandler/19.3.0"
}

object HostInfoTypes {
    const val IP_V4 = "ipv4"
    const val IP_V6 = "ipv6"
    const val DOMAIN_NAME = "domain_name"
}

// TS 26.512 V19.3.0 table 7.8.1-1: the CMCD metrics scheme identifiers, one per class of CMCD information (CTA-5004)
object CmcdSchemes {
    const val SESSION = "urn:3gpp:5gms:metrics:common-media-client-data:session"
    const val OBJECT = "urn:3gpp:5gms:metrics:common-media-client-data:object"
    const val REQUEST = "urn:3gpp:5gms:metrics:common-media-client-data:request"
    const val STATUS = "urn:3gpp:5gms:metrics:common-media-client-data:status"

    /** The keys of each class of CMCD version 1 (CTA-5004 table 2) */
    val KEYS: Map<String, Set<String>> = mapOf(
        SESSION to setOf("cid", "pr", "sf", "sid", "st", "v"),
        OBJECT to setOf("br", "d", "ot", "tb"),
        REQUEST to setOf("bl", "dl", "mtp", "nor", "nrr", "su"),
        STATUS to setOf("bs", "rtp")
    )
}

// TS 26.512 V19.3.0 table 12.4-1: the values of the client-data-reporting-mode parameter of the 3GPP Service URL
object ClientDataReportingModes {
    const val CMCD_HEADERS = "cmcd-headers"
    const val CMCD_QUERY = "cmcd-query"
}

object MetricReportingSchemes {
    const val FIVE_G_MAG_EXOPLAYER_COMBINED_PLAYBACK_STATS = "urn:5gmag:exoplayer:combined"
    const val THREE_GPP_DASH_METRIC_REPORTING = "urn:3GPP:ns:PSS:DASH:QM10"
}

// The term identifiers of TS 26.512 V19.3.0 clause E.2.1 are the key names of TS 26.247 clause 10.2
object Metrics {
    const val BUFFER_LEVEL = "urn:3GPP:ns:PSS:DASH:QM10#BufferLevel"
    const val REP_SWITCH_LIST = "urn:3GPP:ns:PSS:DASH:QM10#RepSwitchList"
    const val MPD_INFORMATION = "urn:3GPP:ns:PSS:DASH:QM10#MPDInformation"
    const val INITIAL_PLAYOUT_DELAY = "urn:3GPP:ns:PSS:DASH:QM10#InitialPlayoutDelay"
    const val PLAYOUT_DELAY_FOR_MEDIA_STARTUP = "urn:3GPP:ns:PSS:DASH:QM10#PlayoutDelayforMediaStartup"
    const val DEVICE_INFORMATION = "urn:3GPP:ns:PSS:DASH:QM10#DeviceInformationList"
    const val AVG_THROUGHPUT = "urn:3GPP:ns:PSS:DASH:QM10#AvgThroughput"
    const val PLAY_LIST = "urn:3GPP:ns:PSS:DASH:QM10#PlayList"
}

object XmlSchemaStrings {
    object THREE_GPP_METADATA_2017_HSD_RECEPTION_REPORT {
        const val SCHEMA = "urn:3gpp:metadata:2017:HSD:receptionreport"
        const val LOCATION = "DASH-QoE-Report.xsd"
        const val XSI = "http://www.w3.org/2001/XMLSchema-instance"
        const val SV = "urn:3gpp:metadata:2016:PSS:schemaVersion"
    }
}