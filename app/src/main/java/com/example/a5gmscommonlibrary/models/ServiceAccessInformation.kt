package com.example.a5gmscommonlibrary.models

data class ServiceAccessInformation(
    val provisioningSessionId : String,
    val provisioningSessionType: String?,
    val streamingAccess: StreamingAccess
)

data class StreamingAccess(
    val mediaPlayerEntry: String
)
