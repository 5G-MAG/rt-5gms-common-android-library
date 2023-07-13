package com.fivegmag.a5gmscommonlibrary

import com.fivegmag.a5gmscommonlibrary.helpers.Utils
import org.junit.Assert.assertEquals
import org.junit.Test

class UtilsUnitTest {

    @Test
    fun addTrailingSlash() {
        val target = "http://localhost/v2/"
        val test = "http://localhost/v2"
        val utils = Utils()
        val final = utils.addTrailingSlashIfNeeded(test)

        assertEquals(target, final)
    }

    @Test
    fun doNotAddSecondTrailingSlash() {
        val target = "http://localhost/v2/"
        val test = "http://localhost/v2/"
        val utils = Utils()
        val final = utils.addTrailingSlashIfNeeded(test)

        assertEquals(target, final)
    }
}