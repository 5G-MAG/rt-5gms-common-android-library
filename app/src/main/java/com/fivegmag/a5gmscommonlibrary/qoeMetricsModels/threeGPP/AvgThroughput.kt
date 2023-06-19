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
