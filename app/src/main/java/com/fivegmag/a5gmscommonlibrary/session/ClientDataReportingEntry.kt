package com.fivegmag.a5gmscommonlibrary.session

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * One client data reporting configuration for CMCD that the Media Player Entry declares (TS 26.512 V19.3.0 clauses 10.5.1 and G.5.2.2, ISO/IEC 23009-1:2026
 * clauses K.3.7 and K.4.2.7: one CMCDParameters element with the filters of its ClientDataReporting), as the Media Session Handler reads it from the manifest. A null field says that the Media Player Entry gave nothing for it.
 */
@Parcelize
data class ClientDataReportingEntry(
    /** The URLs of the BaseURL elements of the service locations that the reporting is restricted to; null: every service location, empty: none could be resolved */
    val serviceLocationUrls: ArrayList<String>? = null,
    /** The CMCD object types (ot values) of the request types of includeInRequests (ISO table I.4) that the reporting is restricted to; null: every request */
    val objectTypes: ArrayList<String>? = null,
    /** ClientDataReportingModes: how the CMCD data is sent, unless the Service URL or the settings API say otherwise (the entry's @mode, query if absent) */
    val mode: String? = null,
    /** The session identifier of the entry (at most 64 characters), for the CMCD sid key if the keys name it */
    val sessionId: String? = null,
    /** The content identifier of the entry (at most 64 characters), for the CMCD cid key if the keys name it */
    val contentId: String? = null,
    /** The explicit set of CMCD keys, reported in addition to those of the client metrics reporting configurations */
    val keys: ArrayList<String>? = null,
    /** The URLs of the other service locations of the MPD, that the entry does not name: a request under one of them that is longer than the entry's URL belongs to that location (BaseURL elements can be nested); null: none */
    val otherServiceLocationUrls: ArrayList<String>? = null,
    /** The @id of the ServiceDescription that declares the entry (ISO/IEC 23009-1:2026 table K.10), which the settings API of TS 26.512 V19.3.0 clause 13.2.4 selects or modifies by its identifier; null: none valid */
    val serviceDescriptionId: Long? = null,
    /** The identifiers of the Adaptation Sets that the reporting is restricted to (table K.7); null: every Adaptation Set. The player evaluates it with [MediaPlayerEntryContext.adaptationSetIds] */
    val adaptationSets: ArrayList<Long>? = null,
    /** true: the description has the Scope urn:mpeg:dash:event:service-description:2024 (ISO/IEC 23009-1:2026 clause K.6) and applies only while an Event activates it */
    val activatedByEvent: Boolean = false
) : Parcelable
