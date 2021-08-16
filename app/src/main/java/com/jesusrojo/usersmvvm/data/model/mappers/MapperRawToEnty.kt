package com.jesusrojo.usersmvvm.data.model.mappers

import com.jesusrojo.usersmvvm.data.model.User
import com.jesusrojo.usersmvvm.data.model.UserRaw


class MapperRawToEnty : Function1<List<UserRaw>, List<User>> {

    companion object {
        var countStatic = 0
    }

    override fun invoke(rawDatas: List<UserRaw>): List<User> {
        return rawDatas.map {
            //Timber.d("rawData.toString $it ##")
            var userId: Int = -1
            var name = ""
            var username = ""
            var email = ""
            var phone = ""
            var website = ""
            var address = ""
            var company = ""

            val userIdRaw = it.userId
            if (userIdRaw != null) userId = userIdRaw

            val nameRaw = it.name
            if (nameRaw != null) name = nameRaw + " " + getNextCount()

            val userNameRaw = it.userName
            if (userNameRaw != null) username = userNameRaw

            val emailRaw = it.email
            if (emailRaw != null) email = emailRaw

            val phoneRaw = it.phone
            if (phoneRaw != null) phone = phoneRaw

            val websiteRaw = it.website
            if (websiteRaw != null) website = websiteRaw

            val addressRaw = it.address?.toString()
            if (addressRaw != null) address = addressRaw

            val companyRaw = it.company?.toString()
            if (companyRaw != null) company = companyRaw

            User(userId, name, username, email, phone, website, address, company)
        }
    }

    private fun getNextCount(): Int {
        countStatic++
        return countStatic
    }
}