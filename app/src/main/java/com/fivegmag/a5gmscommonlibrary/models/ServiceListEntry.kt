/*
License: 5G-MAG Public License (v1.0)
Author: Daniel Silhavy
Copyright: (C) 2023 Fraunhofer FOKUS
For full license terms please see the LICENSE file distributed with this
program. If this file is missing then the license can be retrieved from
https://drive.google.com/file/d/1cinCiA778IErENZ3JN52VFW-1ffHpx7Z/view
*/

package com.fivegmag.a5gmscommonlibrary.models
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ServiceListEntry(
    /** The externalServiceId of the Provisioning Session (TS 26.510 table 8.2.3.1-1): the key of the Service Access Information at M5 */
    val externalServiceId : String,
    val name: String,
    val entryPoints: ArrayList<EntryPoint>?
) : Parcelable

@Parcelize
data class EntryPoint(
    val locator: String,
    val contentType: String?,
    val profiles: ArrayList<String>?,
    val protocol: String? = null // an AbsoluteMediaEntryPoint has a contentType or a protocol
) : Parcelable

