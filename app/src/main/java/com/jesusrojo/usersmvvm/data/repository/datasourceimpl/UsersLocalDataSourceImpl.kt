package com.jesusrojo.usersmvvm.data.repository.datasourceimpl

import com.jesusrojo.usersmvvm.data.db.UsersDAO
import com.jesusrojo.usersmvvm.data.model.User
import com.jesusrojo.usersmvvm.data.repository.datasource.UsersLocalDataSource
import javax.inject.Inject


class UsersLocalDataSourceImpl @Inject constructor (
    private val myDao: UsersDAO
) : UsersLocalDataSource {

    override suspend fun fetchAllInDB(): List<User> {
        return myDao.fetchAll()
    }

    override suspend fun saveAllToDB(enties: List<User>) {
        myDao.insertAll(enties)
    }

    override suspend fun deleteAllInDB() {
        myDao.deleteAll()
    }
}