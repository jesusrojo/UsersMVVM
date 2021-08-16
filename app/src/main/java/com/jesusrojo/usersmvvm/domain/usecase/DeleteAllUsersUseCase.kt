package com.jesusrojo.usersmvvm.domain.usecase

import com.jesusrojo.usersmvvm.domain.repository.UsersRepository
import javax.inject.Inject


class DeleteAllUsersUseCase @Inject constructor (
    private val repository: UsersRepository) {
    suspend fun execute() = repository.deleteAll()
}