package com.fivegmag.a5gmscommonlibrary.helpers

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.ObjectMapper

// android.os.Parcelable has a public getStability() since API 29 (value 0, STABILITY_LOCAL). Jackson takes it for a bean property, so every
// Parcelable data class would carry an extra "stability" property that the 3GPP data types do not define.
abstract class ParcelableStabilityMixIn {
    @JsonIgnore
    abstract fun getStability(): Int
}

// Leaves the "stability" property of android.os.Parcelable out of what the mapper writes
fun <T : ObjectMapper> T.ignoreParcelableStability(): T {
    addMixIn(Parcelable::class.java, ParcelableStabilityMixIn::class.java)
    return this
}
