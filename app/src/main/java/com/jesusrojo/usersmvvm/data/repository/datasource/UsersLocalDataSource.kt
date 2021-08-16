package com.jesusrojo.usersmvvm.data.repository.datasource

import com.jesusrojo.usersmvvm.data.model.User

interface UsersLocalDataSource {

    suspend fun fetchAllInDB(): List<User>
    suspend fun saveAllToDB(datas: List<User>)
    suspend fun deleteAllInDB()
}