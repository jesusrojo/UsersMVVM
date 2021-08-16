package com.jesusrojo.usersmvvm.data.db

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.jesusrojo.usersmvvm.data.model.User
import com.jesusrojo.usersmvvm.utils.BaseUITest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UsersDaoTest: BaseUITest() {

    private lateinit var dao: UsersDAO
    private lateinit var database: UsersDatabase

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            UsersDatabase::class.java
        ).build()
        dao = database.getMyDAO()
    }

    override fun tearDownChild() {
        database.close()
    }

    @Test
    fun saveTest() = runBlocking {
        val enties = prepareDatas()
        dao.insertAll(enties)
        val resultEnties = dao.fetchAll()
        assertThat(resultEnties).isEqualTo(enties)
    }



    @Test
    fun deleteTest() = runBlocking {
        val enties = prepareDatas()
        dao.insertAll(enties)
        dao.deleteAll()
        val resultEnties = dao.fetchAll()
        assertThat(resultEnties).isEmpty()
    }

    private fun prepareDatas(): List<User> {
        return listOf(getUser("1"),getUser("2")
        )
    }

    private fun getUser(flag: String): User {
        val userId: Int = -1
        val name = "name$flag"
        val username = "username$flag"
        val email = "email$flag"
        val phone = "phone$flag"
        val website = "website$flag"
        val address = "address$flag"
        val company = "company$flag"
        return User(userId, name, username, email, phone, website, address, company)
    }
}