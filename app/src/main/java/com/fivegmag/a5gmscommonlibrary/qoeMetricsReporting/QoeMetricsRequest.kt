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
    var scheme: String = "",
    var reportPeriod: Long? = 0,
    var metrics: ArrayList<String>? = ArrayList(),
    var metricReportingConfigurationId: String? = ""
) : Parcelable
