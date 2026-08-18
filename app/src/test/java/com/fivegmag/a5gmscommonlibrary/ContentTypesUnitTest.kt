package com.fivegmag.a5gmscommonlibrary

import com.fivegmag.a5gmscommonlibrary.helpers.ContentTypes
import org.junit.Test

import org.junit.Assert.*

class ContentTypesUnitTest {

    @Test
    fun cmmfContentTypeMatchesRegisteredMimeType() {
        assertEquals(
            "application/vnd.cmmf-configuration-information+json",
            ContentTypes.CMMF
        )
    }
}
