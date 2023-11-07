package com.fivegmag.a5gmscommonlibrary.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlaybackRequest(
    val entryPoints: ArrayList<EntryPoint>,
    val playbackConsumptionReportingConfiguration: PlaybackConsumptionReportingConfiguration
) : Parcelable

@Parcelize
data class PlaybackConsumptionReportingConfiguration(
    var accessReporting : Boolean? = false,
    var locationReporting : Boolean? = false
) : Parcelable