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
}

object SessionHandlerEvents {
    object Notification {
        const val SESSION_HANDLING_ACTIVATED = "SESSION_HANDLING_ACTIVATED"
        const val SESSION_HANDLING_STOPPED = "SESSION_HANDLING_STOPPED"
    }
}

object ContentTypes {
    const val DASH = "application/dash+xml"
    const val HLS = "application/vnd.apple.mpegurl"
    const val JSON = "application/json"
    const val XML = "application/xml"
}

object UserAgentTokens {
    const val FIVE_G_MS_REL_17_MEDIA_STREAM_HANDLER = "5GMSMediaStreamHandler/17"
    const val FIVE_G_MS_REL_17_MEDIA_SESSION_HANDLER = "5GMSMediaSessionHandler/17"
}

object HostInfoTypes {
    const val IP_V4 = "ipv4"
    const val IP_V6 = "ipv6"
    const val DOMAIN_NAME = "domain_name"
}

object MetricReportingSchemes {
    const val FIVE_G_MAG_EXOPLAYER_COMBINED_PLAYBACK_STATS = "urn:5gmag:exoplayer:combined"
    const val THREE_GPP_DASH_METRIC_REPORTING = "urn:3GPP:ns:PSS:DASH:QM10"
}

object Metrics {
    const val BUFFER_LEVEL = "urn:3GPP:ns:PSS:DASH:QM10#BufferLevel"
    const val HTTP_LIST = "urn:3GPP:ns:PSS:DASH:QM10#HTTPList"
    const val REP_SWITCH_LIST = "urn:3GPP:ns:PSS:DASH:QM10#RepSwitchList"
    const val MPD_INFORMATION = "urn:3GPP:ns:PSS:DASH:QM10#MPDInformation"
}

object XmlSchemaStrings {
    object THREE_GPP_METADATA_2011_HSD_RECEPTION_REPORT {
        const val SCHEMA = "urn:3gpp:metadata:2011:HSD:receptionreport"
        const val LOCATION = "DASH-QoE-Report.xsd"
        const val XSI = "http://www.w3.org/2001/XMLSchema-instance"
        const val SV = "urn:3gpp:metadata:2016:PSS:schemaVersion"
    }
}