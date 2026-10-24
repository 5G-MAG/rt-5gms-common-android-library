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

/**
 * TS 26.510 V19.2.0 table 7.3.3.11-1: a domain name and the port numbers of the endpoint. The table has no property for an IP
 * address; where the endpoint is known only by address, the textual address is carried in domainName (specification defect SD-019,
 * decided by the owner on 2026-10-24: follow the table and the OpenAPI for now).
 */
@Parcelize
data class EndpointAddress(
    val domainName: String,
    val portNumbers: List<Int>
) : Parcelable

@Parcelize
enum class CellIdentifierType : Parcelable {
    CGI,
    ECGI,
    NCGI
}
data class HostInfo(
    val type: String,
    val host: String
)