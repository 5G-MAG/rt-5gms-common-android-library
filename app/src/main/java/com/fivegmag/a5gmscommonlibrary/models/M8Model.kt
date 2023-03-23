package com.fivegmag.a5gmscommonlibrary.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class M8Model(
    var m5Url: String,
    var serviceList: ArrayList<ServiceListEntry>
) : Parcelable
