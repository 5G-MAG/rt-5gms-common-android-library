package com.fivegmag.a5gmscommonlibrary.consumptionReporting

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConsumptionReportRequest(
    var accessReporting : Boolean? = false,
    var locationReporting: Boolean? = false
) : Parcelable