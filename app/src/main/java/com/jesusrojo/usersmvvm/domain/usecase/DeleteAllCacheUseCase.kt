package com.jesusrojo.usersmvvm.domain.usecase

import com.jesusrojo.usersmvvm.domain.repository.UsersRepository
import javax.inject.Inject


class DeleteAllCacheUseCase @Inject constructor (
    private val repository: UsersRepository) {
    suspend fun execute() = repository.deleteAllCache()
}