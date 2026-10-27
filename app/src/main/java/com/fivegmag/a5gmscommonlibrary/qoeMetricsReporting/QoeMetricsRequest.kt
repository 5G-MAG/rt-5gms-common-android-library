/*
License: 5G-MAG Public License (v1.0)
Author: Daniel Silhavy
Copyright: (C) 2023 Fraunhofer FOKUS
For full license terms please see the LICENSE file distributed with this
program. If this file is missing then the license can be retrieved from
https://drive.google.com/file/d/1cinCiA778IErENZ3JN52VFW-1ffHpx7Z/view
*/

package com.fivegmag.a5gmscommonlibrary.qoeMetricsReporting

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QoeMetricsRequest(
    var scheme: String? = "",
    var samplingPeriod: Long,
    var reportingInterval: Long? = 0,
    var metrics: ArrayList<String>? = ArrayList(),
    var metricsReportingConfigurationId: String = "",
    // TS 26.510 V19.2.0 clause 5.2.11.1: metric (URI) to the threshold values whose crossing is to be reported
    var positiveCrossingThresholds: HashMap<String, ArrayList<Float>>? = null,
    var negativeCrossingThresholds: HashMap<String, ArrayList<Float>>? = null,
    // TS 26.510 V19.2.0 table 8.7.3.1-1: the part of the media delivery session (seconds from its start) for which metrics are reported
    var reportingStartOffset: Long? = null,
    var reportingDuration: Long? = null,
    // the configuration has a locationFilter: the Media Session Handler says by CMCD_LOCATION_UPDATE when the UE is inside it
    var locationFiltered: Boolean = false,
) : Parcelable {
    override fun describeContents(): Int = 0
}
