package com.jesusrojo.usersmvvm.domain.usecase

import com.jesusrojo.usersmvvm.data.model.User
import com.jesusrojo.usersmvvm.domain.repository.UsersRepository
import javax.inject.Inject

class FetchUsersUseCase @Inject constructor(
    private val repository: UsersRepository) {
    suspend fun execute(): List<User>? = repository.fetchUsers()
}