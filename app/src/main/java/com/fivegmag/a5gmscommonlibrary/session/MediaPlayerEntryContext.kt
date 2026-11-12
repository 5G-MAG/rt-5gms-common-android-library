package com.fivegmag.a5gmscommonlibrary.session

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/** The URLs of the BaseURL elements of the Media Player Entry that carry one service location label (ISO/IEC 23009-1:2026 clause 5.6.6) */
@Parcelize
data class ServiceLocationUrls(val label: String, val urls: ArrayList<String>) : Parcelable

/**
 * What the player needs to know of the Media Player Entry to apply the service description settings of TS 26.512 V19.3.0 clause 13.2.4
 * (serviceDescriptions[], serviceDescriptionId) to it, as the Media Session Handler reads it from the manifest.
 */
@Parcelize
data class MediaPlayerEntryContext(
    /** The service location labels of the manifest with the directories of their BaseURL elements, resolved against the URL of the manifest */
    val serviceLocations: ArrayList<ServiceLocationUrls> = ArrayList(),
    /** The @id attribute of every AdaptationSet of the manifest, as written; empty if it has none */
    val adaptationSetIds: ArrayList<String> = ArrayList(),
    /** The @id of the ServiceDescription elements that the player can select (those without a Scope) that have a valid identifier */
    val serviceDescriptionIds: ArrayList<Long> = ArrayList()
) : Parcelable
