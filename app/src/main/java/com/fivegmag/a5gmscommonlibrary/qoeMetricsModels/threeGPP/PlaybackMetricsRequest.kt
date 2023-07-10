package com.fivegmag.a5gmscommonlibrary.qoeMetricsModels.threeGPP

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlaybackMetricsRequest(
    var schema : String = "",
    var reportPeriod: Long = 0
) : Parcelable
