package com.jesusrojo.usersmvvm.data.model

import com.google.common.truth.Truth.assertThat
import org.junit.Test



class UserTest {
    private val userId: Int = -1
    private val name = "name1"
    private val username = "userName1"
    private val email = "email1"
    private val phone = "phone1"
    private val website = "website1"
    private val address = "address1"
    private val company = "company1"

    private val sut = User(userId, name, username, email, phone, website, address, company)

    @Test
    fun getUserId() {
        assertThat(userId).isEqualTo(sut.userId)
    }

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