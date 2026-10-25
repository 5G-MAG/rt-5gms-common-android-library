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
    val mediaStreamingSessionIdentifier: String,
    /** The media delivery session identifier (TS 26.510 V19.2.0 clause 7.3.2): the CMCD sid key (TS 26.512 V19.3.0 clause 10.5.1) */
    val mediaDeliverySessionIdentifier: String? = null,
    /** The content identifier of the media being consumed, if the application gave one: the CMCD cid key */
    val contentIdentifier: String? = null
) : Parcelable
