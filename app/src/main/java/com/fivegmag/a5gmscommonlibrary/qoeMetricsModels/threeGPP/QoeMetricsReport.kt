package com.fivegmag.a5gmscommonlibrary.qoeMetricsModels.threeGPP

import android.os.Parcelable
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "QoeReport")
data class QoeMetricsReport(
    @field:ElementList(name = "QoeMetric") val entries: List<Any>
)


