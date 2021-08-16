package com.jesusrojo.usersmvvm.domain.usecase

import com.jesusrojo.usersmvvm.data.model.User
import com.jesusrojo.usersmvvm.domain.repository.UsersRepository
import com.jesusrojo.usersmvvm.utils.Resource
import javax.inject.Inject

class FetchUsersUseCase @Inject constructor(
    private val repository: UsersRepository) {
    suspend fun execute(): Resource<List<User>> = repository.fetchUsers()
}