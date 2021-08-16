package com.jesusrojo.usersmvvm.data.model

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class CompanyTest {

    private val bs = "bs1"
    private val catchPhrase = "catchPhrase1"
    private val companyName = "companyName1"
    private val sut = Company(bs, catchPhrase, companyName)

    @Test
    fun getBs() {
        assertThat(bs).isEqualTo(sut.bs)
    }

    @Test
    fun getCatchPhrase() {
        assertThat(catchPhrase).isEqualTo(sut.catchPhrase)
    }

    @Test
    fun getType() {
        assertThat(companyName).isEqualTo(sut.companyName)
    }
}