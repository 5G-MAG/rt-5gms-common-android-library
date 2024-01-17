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
data class TypedLocation(
    val locationIdentifierType: CellIdentifierType,
    val location: String
) : Parcelable

@Parcelize
data class EndpointAddress(
    val domainName: String? = null,
    val ipv4Addr: String? = null,
    val ipv6Addr: String? = null,
    val portNumber: Int
) : Parcelable

@Parcelize
enum class CellIdentifierType : Parcelable {
    CGI,
    ECGI,
    NCGI
}