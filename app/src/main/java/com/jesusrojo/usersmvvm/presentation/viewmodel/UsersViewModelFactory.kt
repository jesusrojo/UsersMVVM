package com.jesusrojo.usersmvvm.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jesusrojo.usersmvvm.domain.usecase.*

import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


class UsersViewModelFactory @Inject constructor(
        private val fetchUsersUseCase: FetchUsersUseCase,
        private val deleteAllUsersUseCase: DeleteAllUsersUseCase,
        private val ioDispatcher: CoroutineDispatcher
):ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
            return UsersViewModel(
                    fetchUsersUseCase,
                    deleteAllUsersUseCase,
                    ioDispatcher
            ) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }
}