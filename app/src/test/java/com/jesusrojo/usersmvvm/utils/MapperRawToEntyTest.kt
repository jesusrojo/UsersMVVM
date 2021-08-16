package com.jesusrojo.usersmvvm.utils


import com.jesusrojo.usersmvvm.data.model.mappers.MapperRawToEnty
import com.jesusrojo.usersmvvm.data.repository.FakeRepository
import org.junit.Test

import org.junit.Assert.*

class MapperRawToEntyTest: BaseUnitTest() {

    private val sut = MapperRawToEnty()

    private val userRaw = FakeRepository.getUserRaw("1")
    private val users = sut(listOf(userRaw))
    private val userIndexZero = users[0]

    @Test
    fun keepSameId() {
        assertEquals(userRaw.userId, userIndexZero.userId)
    }

    @Test
    fun keepSameName() {
        assertEquals(userRaw.name + " 2", userIndexZero.name)
        //error  this is for the static number we add
//        org.junit.ComparisonFailure:
//        Expected :name1
//        Actual   :name1 2
    }

    @Test
    fun keepSameUserName() {
        assertEquals(userRaw.userName, userIndexZero.userName)
    }

    @Test
    fun keepSameEmail() {
        assertEquals(userRaw.email, userIndexZero.email)
    }

    @Test
    fun keepSameWebsite() {
        assertEquals(userRaw.website, userIndexZero.website)
    }

}