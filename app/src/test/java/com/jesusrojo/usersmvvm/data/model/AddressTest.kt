package com.jesusrojo.usersmvvm.data.model

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class AddressTest {
    private val city = "city1"
    private val lat = "lat1"
    private val lng = "lng1"
    private val geo = Geo(lat, lng)
    private val street = "street1"
    private val suite = "suite1"
    private val zipcode = "zipcode1"
    private val sut = Address(city, geo, street, suite, zipcode)

    @Test
    fun getCity() {
       assertThat(city).isEqualTo(sut.city)
    }
    @Test
    fun getAll() {
        assertThat(lat).isEqualTo(sut.geo.lat)
        assertThat(lng).isEqualTo(sut.geo.lng)
        assertThat(street).isEqualTo(sut.street)
        assertThat(suite).isEqualTo(sut.suite)
        assertThat(zipcode).isEqualTo(sut.zipcode)
    }
}