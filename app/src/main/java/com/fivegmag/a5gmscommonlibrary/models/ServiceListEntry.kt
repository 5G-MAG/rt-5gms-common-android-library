package com.fivegmag.a5gmscommonlibrary.models
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ServiceListEntry(
    val provisioningSessionId : String,
    val name: String,
    val entryPoints: ArrayList<EntryPoint>?
) : Parcelable

@Parcelize
data class EntryPoint(
    val locator: String,
    val contentType: String,
    val profiles: ArrayList<String>?
) : Parcelable

