package com.fivegmag.a5gmscommonlibrary.qoeMetricsModels.threeGPP

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlaybackMetricsResponse(
    var metricsString : String = "",
    var metricsReportingConfigurationId : String? = ""
) : Parcelable
