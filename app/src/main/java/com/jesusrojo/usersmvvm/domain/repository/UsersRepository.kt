package com.jesusrojo.usersmvvm.domain.repository


import com.jesusrojo.usersmvvm.data.model.User
import com.jesusrojo.usersmvvm.utils.Resource
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    suspend fun fetchUsers(): Resource<List<User>>
    suspend fun deleteAll()
}