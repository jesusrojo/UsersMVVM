package com.jesusrojo.usersmvvm.presentation.viewmodel

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.jesusrojo.usersmvvm.utils.*
import com.jesusrojo.usersmvvm.domain.usecase.DeleteAllUsersUseCase
import com.jesusrojo.usersmvvm.domain.usecase.FetchUsersUseCase

import kotlinx.coroutines.*


abstract class BaseUsersViewModel(
        protected val fetchUsersUseCase: FetchUsersUseCase,
        protected val deleteAllUsersUseCase: DeleteAllUsersUseCase,
        protected val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    protected val vmScope = viewModelScope
    protected var datasJob: Job? = null
    protected var nextDatasJob: Job? = null
    protected var refreshJob: Job? = null
    protected var deleteJob: Job? = null

    init { DebugHelp.l("init Base viewModel") }

    @CallSuper
    override fun onCleared() {
        println("onCleared")// DebugHelp.l("onCleared") NOT WORKING
        deleteJob?.cancel()
        refreshJob?.cancel()
        nextDatasJob?.cancel()
        datasJob?.cancel()
        super.onCleared()
    }
}