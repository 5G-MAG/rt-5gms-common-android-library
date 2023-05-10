package com.fivegmag.a5gmscommonlibrary

import com.fivegmag.a5gmscommonlibrary.models.EntryPoint
import com.fivegmag.a5gmscommonlibrary.models.M8Model
import com.fivegmag.a5gmscommonlibrary.models.ServiceListEntry
import org.junit.Test

import org.junit.Assert.*

class M8ModelUnitTest {

    @Test
    fun createModel() {
        var profiles = ArrayList<String>()
        profiles.add("profile1");

        var firstEntryPoint = EntryPoint("locator1", "contentType1", profiles)
        var firstEntryPointList = ArrayList<EntryPoint>()
        firstEntryPointList.add(firstEntryPoint)

        var secondEntryPoint = EntryPoint("locator2", "contentType2", profiles)
        var secondEntryPointList = ArrayList<EntryPoint>()
        secondEntryPointList.add(secondEntryPoint)

        var firstServiceListEntry = ServiceListEntry("id1", "name1", firstEntryPointList)
        var secondServiceListEntry = ServiceListEntry("id2", "name2", secondEntryPointList)
        var serviceList = ArrayList<ServiceListEntry>()
        serviceList.add(firstServiceListEntry)
        serviceList.add(secondServiceListEntry)

        var m5BaseUrl = "m5BaseUrl"
        var m8Model = M8Model(m5BaseUrl, serviceList)

        assertEquals(m5BaseUrl, m8Model.m5BaseUrl)
        assertEquals("id1", m8Model.serviceList.get(0).provisioningSessionId)
        assertEquals("id2", m8Model.serviceList.get(1).provisioningSessionId)
        assertEquals("name1", m8Model.serviceList.get(0).name)
        assertEquals("name2", m8Model.serviceList.get(1).name)
        assertEquals("locator1", m8Model.serviceList.get(0).entryPoints?.get(0)?.locator ?: "fail")
        assertEquals("contentType1", m8Model.serviceList.get(0).entryPoints?.get(0)?.contentType ?: "fail")
        assertEquals("locator2", m8Model.serviceList.get(1).entryPoints?.get(0)?.locator ?: "fail")
        assertEquals("contentType2", m8Model.serviceList.get(1).entryPoints?.get(0)?.contentType ?: "fail")
    }
}