package com.jesusrojo.usersmvvm.data.repository.datasourceimpl

import com.jesusrojo.usersmvvm.data.model.User
import com.jesusrojo.usersmvvm.data.repository.datasource.UsersCacheDataSource
import com.jesusrojo.usersmvvm.utils.DebugHelp
import javax.inject.Inject


class UsersCacheDataSourceImpl @Inject constructor()
    : UsersCacheDataSource {

    private var datas = ArrayList<User>()

    override suspend fun fetchDatasFromCache(): List<User> {
        DebugHelp.l("fetchDatasFromCache")
        return datas
    }

    override suspend fun saveDatasToCache(datas: List<User>) {
        this.datas.clear()
        this.datas = ArrayList(datas)
    }

    override suspend fun deleteAllInCache() {
        datas.clear()
    }
}