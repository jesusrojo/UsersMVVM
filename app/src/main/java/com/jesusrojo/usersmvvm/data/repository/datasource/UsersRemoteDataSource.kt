package com.jesusrojo.usersmvvm.data.repository.datasource

import com.jesusrojo.usersmvvm.data.model.UserRaw
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface UsersRemoteDataSource {
    suspend fun fetchUsers(): Response<List<UserRaw>>
    suspend fun fetchUsersFlow(): Flow<Result<List<UserRaw>>>
}