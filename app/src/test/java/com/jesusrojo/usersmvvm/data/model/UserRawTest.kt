package com.jesusrojo.usersmvvm.data.model

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class UserRawTest {

    private var userId: Int = -1
    private var name = "name1"
    private var username = "username1"
    private var email = "email1"
    private var phone = "phone1"
    private var website = "website1"

    private val city: String = "city1"
    private val lat: String = "lat1"
    private val lng: String = "lng1"
    private val geo: Geo = Geo(lat, lng)
    private val street: String = "street1"
    private val suite: String = "suite1"
    private val zipcode: String = "zipcode1"
    private val address = Address(city, geo, street, suite, zipcode)

    private val bs = "bs1"
    private val catchPhrase = "catchPhrase1"
    private val companyName = "companyName1"
    private val company = Company(bs, catchPhrase, companyName)

    private val sut = UserRaw(userId, name, username, email, phone, website, address, company)

    @Test
    fun getName() {
        assertThat(name).isEqualTo(sut.name)
    }

    @Test
    fun getUserName() {
        assertThat(username).isEqualTo(sut.userName)
    }

    @Test
    fun getEmail() {
        assertThat(email).isEqualTo(sut.email)
    }

    @Test
    fun getPhone() {
        assertThat(phone).isEqualTo(sut.phone)
    }

    @Test
    fun getWebsite() {
        assertThat(website).isEqualTo(sut.website)
    }

    @Test
    fun getAddress() {
        assertThat(address).isEqualTo(sut.address)
    }

    @Test
    fun getCompany() {
        assertThat(company).isEqualTo(sut.company)
    }
}