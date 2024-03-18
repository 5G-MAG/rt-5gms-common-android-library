package com.fivegmag.a5gmscommonlibrary.session

import android.os.Parcelable
import com.fivegmag.a5gmscommonlibrary.consumptionReporting.ConsumptionRequest
import com.fivegmag.a5gmscommonlibrary.models.EntryPoint
import com.fivegmag.a5gmscommonlibrary.qoeMetricsReporting.QoeMetricsRequest
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlaybackRequest(
    val entryPoints: ArrayList<EntryPoint>,
    val consumptionRequest: ConsumptionRequest,
    val qoeMetricsRequests: ArrayList<QoeMetricsRequest>,
    val mediaStreamingSessionIdentifier: String
) : Parcelable
