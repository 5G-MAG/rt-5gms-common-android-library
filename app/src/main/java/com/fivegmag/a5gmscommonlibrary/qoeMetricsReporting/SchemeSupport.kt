package com.fivegmag.a5gmscommonlibrary.qoeMetricsReporting

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SchemeSupport(
    val scheme: String,
    val supported: Boolean
) : Parcelable
