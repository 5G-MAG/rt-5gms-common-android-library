package com.fivegmag.a5gmscommonlibrary.qoeMetricsModels.threeGPP

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlaybackMetricsRequest(
    var scheme : String = "",
    var reportPeriod: Long? = 0,
    var metrics : ArrayList<String>? = ArrayList(),
    var metricReportingConfigurationId : String? = ""
) : Parcelable
