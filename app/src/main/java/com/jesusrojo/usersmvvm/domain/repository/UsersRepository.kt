package com.jesusrojo.usersmvvm.domain.repository


import com.jesusrojo.usersmvvm.data.model.User
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    suspend fun fetchUsersFlow(): Flow<Result<List<User>>>
    suspend fun fetchUsers(): List<User>?
    suspend fun deleteAll()
}