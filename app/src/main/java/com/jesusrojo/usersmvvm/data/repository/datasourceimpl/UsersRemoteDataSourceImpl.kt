package com.jesusrojo.usersmvvm.data.repository.datasourceimpl

import com.jesusrojo.usersmvvm.data.api.UsersAPIService
import com.jesusrojo.usersmvvm.data.model.UserRaw
import com.jesusrojo.usersmvvm.data.repository.datasource.UsersRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject


class UsersRemoteDataSourceImpl @Inject constructor(
        private val service: UsersAPIService
) : UsersRemoteDataSource {

    override suspend fun fetchUsers(): Response<List<UserRaw>> =
        service.fetchUsers()

    override suspend fun fetchUsersFlow(): Flow<Result<List<UserRaw>>> {
        return flow {
            val response = service.fetchUsers()
            val rawDatas = response.body()
            ////val body = response.body()
            ////val rawDatas = body?.items

            var result = Result.success(rawDatas!!)
            if (rawDatas != null) {
                result = Result.success(rawDatas)
                emit(result)
            } else {
                result = Result.failure(java.lang.RuntimeException("Something went wrong"))
                emit(result)
            }
        }.catch {
            emit(Result.failure(java.lang.RuntimeException("Something went wrong")))
        }
    }

//    private fun prepareFakeList(): List<UserRaw> {
//        val inputStream = javaClass.classLoader!!.getResourceAsStream("users_data_response.json")
//        val source = inputStream.source().buffer()
//        val stringText = source.readString(Charsets.UTF_8)
//        val gson = Gson()
//        val rawDatas: Array<User> = gson.fromJson(stringText,
//                Array<User>::class.java)
//        return rawDatas as List<UserRaw>
//    }
}