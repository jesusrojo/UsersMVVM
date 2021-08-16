package com.jesusrojo.usersmvvm.data.repository.datasource

import com.jesusrojo.usersmvvm.data.model.User


interface UsersCacheDataSource {
    suspend fun fetchDatasFromCache():List<User>
    suspend fun saveDatasToCache(datas: List<User>)
    suspend fun deleteAllInCache()
}