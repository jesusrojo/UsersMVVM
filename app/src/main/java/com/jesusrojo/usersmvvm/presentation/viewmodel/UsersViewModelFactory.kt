package com.jesusrojo.usersmvvm.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jesusrojo.usersmvvm.domain.usecase.*

import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


class UsersViewModelFactory @Inject constructor(
    private val fetchUsersUseCase: FetchUsersUseCase,
    private val deleteAllUseCase: DeleteAllUseCase,
    private val deleteAllCacheUseCase: DeleteAllCacheUseCase,
    private val ioDispatcher: CoroutineDispatcher
):ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
            return UsersViewModel(
                    fetchUsersUseCase,
                    deleteAllUseCase,
                    deleteAllCacheUseCase,
                    ioDispatcher
            ) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }
}