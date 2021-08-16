package com.jesusrojo.usersmvvm.data.repository

import com.jesusrojo.usersmvvm.data.model.*
import com.jesusrojo.usersmvvm.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow


class FakeRepository : UsersRepository {

    private var datas = mutableListOf<User>()

    init {
        datas = getFakeListItemsOneTwo().toMutableList()
    }

    override suspend fun fetchUsers(): List<User>? {
        return datas
    }

    override suspend fun fetchUsersFlow(): Flow<Result<List<User>>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll() {
        datas.clear()
    }


    //FAKE DATA AND DATAS FOR TEST
    companion object {

        fun getUser(flag: String): User {
            val userId: Int = -1
            val name = "name$flag"
            val userName = "userName$flag"
            val email = "email$flag"
            val phone = "phone$flag"
            val website = "website$flag"
            val address = "address$flag"
            val company = "company$flag"
            return User(userId, name, userName, email, phone, website, address, company)
        }

        fun getUserRaw(flag: String): UserRaw {
            val userId: Int = -1
            val name = "name$flag"
            val userName = "userName$flag"
            val email = "email$flag"
            val phone = "phone$flag"
            val website = "website$flag"

            val city = "city$flag"
            val lat = "lat$flag"
            val lng = "lng$flag"
            val geo = Geo(lat, lng)
            val street = "street$flag"
            val suite = "suite$flag"
            val zipcode = "zipcode$flag"
            val address = Address(city, geo, street, suite, zipcode)

            val bs = "bs$flag"
            val catchPhrase = "catchPhrase$flag"
            val companyName = "companyName$flag"
            val company = Company(bs, catchPhrase, companyName)
            return UserRaw(userId, name, userName, email, phone, website, address, company)
        }

        fun getFakeListItemsOneTwo(): List<User> {
            return listOf(getUser("1"), getUser("2"))
        }

        fun getFakeListItemsThreeFour(): List<User> {
            val datas = mutableListOf<User>()
            datas.add(getUser("3"))
            datas.add(getUser("4"))
            return datas
        }

        fun getFakeListGHRepoRawItemsOneTwo(): List<UserRaw> {
            return listOf(getUserRaw("1"), getUserRaw("2"))
        }
    }
}