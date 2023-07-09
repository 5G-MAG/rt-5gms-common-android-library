/*
License: 5G-MAG Public License (v1.0)
Author: Daniel Silhavy
Copyright: (C) 2023 Fraunhofer FOKUS
For full license terms please see the LICENSE file distributed with this
program. If this file is missing then the license can be retrieved from
https://drive.google.com/file/d/1cinCiA778IErENZ3JN52VFW-1ffHpx7Z/view
*/

package com.fivegmag.a5gmscommonlibrary.qoeMetricsModels.threeGPP

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class AvgThroughput(
    val numbytes: Integer,
    val activitytime: Integer,
    val t : String,
    val duration: Integer,
    val accessbearer: String,
    val inactivitytype: InactivityType
)

enum class InactivityType {
    USER_REQUEST,
    CLIENT_MEASURE,
    ERROR
}
