package com.jesusrojo.usersmvvm.presentation.viewmodel

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesusrojo.usersmvvm.domain.usecase.DeleteAllCacheUseCase

import com.jesusrojo.usersmvvm.utils.*
import com.jesusrojo.usersmvvm.domain.usecase.DeleteAllUseCase
import com.jesusrojo.usersmvvm.domain.usecase.FetchUsersUseCase

import kotlinx.coroutines.*


abstract class BaseUsersViewModel(
    protected val fetchUsersUseCase: FetchUsersUseCase,
    protected val deleteAllUseCase: DeleteAllUseCase,
    protected val deleteAllCacheUseCase: DeleteAllCacheUseCase,
    protected val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    protected val vmScope = viewModelScope
    protected var datasJob: Job? = null
    protected var nextDatasJob: Job? = null
    protected var refreshJob: Job? = null
    protected var deleteAllJob: Job? = null
    protected var deleteAllCacheJob: Job? = null

    init { DebugHelp.l("init Base viewModel") }

    @CallSuper
    override fun onCleared() {
        println("onCleared")// DebugHelp.l("onCleared") NOT WORKING
        deleteAllCacheJob?.cancel()
        deleteAllJob?.cancel()
        refreshJob?.cancel()
        nextDatasJob?.cancel()
        datasJob?.cancel()
        super.onCleared()
    }
}