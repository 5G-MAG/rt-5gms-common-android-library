package com.fivegmag.a5gmscommonlibrary.models

data class TypedLocation(
    val locationIdentifierType: String,
    val location: String
)

data class EndpointAddress(
    val ipv4Addr: String? = null,
    val ipv6Addr: String? = null,
    val portNumber: UInt
)