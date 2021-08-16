package com.jesusrojo.usersmvvm.presentation.viewmodel

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jesusrojo.usersmvvm.data.model.User
import com.jesusrojo.usersmvvm.domain.usecase.DeleteAllCacheUseCase
import com.jesusrojo.usersmvvm.utils.DebugHelp
import com.jesusrojo.usersmvvm.utils.Resource
import com.jesusrojo.usersmvvm.utils.tests.wrapEspressoIdlingResource
import com.jesusrojo.usersmvvm.domain.usecase.DeleteAllUseCase
import com.jesusrojo.usersmvvm.domain.usecase.FetchUsersUseCase
import kotlinx.coroutines.*
import javax.inject.Inject


class UsersViewModel @Inject constructor(
    fetchUsersUseCase: FetchUsersUseCase,
    deleteAllUseCase: DeleteAllUseCase,
    deleteAllCacheUseCase: DeleteAllCacheUseCase,
    ioDispatcher: CoroutineDispatcher
) : BaseUsersViewModel(fetchUsersUseCase, deleteAllUseCase, deleteAllCacheUseCase,ioDispatcher) {

    private val _resource: MutableLiveData<Resource<List<User>>> = MutableLiveData()
    val resource: LiveData<Resource<List<User>>> = _resource

    private fun updateUiLoading() {
        _resource.postValue(Resource.Loading())
    }

    private fun updateUiError(message: String) {
        _resource.postValue(Resource.Error(message))
    }

    private fun updateUiResource(newResource: Resource<List<User>>) {
        _resource.postValue(newResource)
    }

    init {
        DebugHelp.l("init viewModel")
    }

    //USE CASES
    fun fetchDatas() {
        datasJob = vmScope.launch(ioDispatcher) {
            fetchDatasFromUseCase()
        }
    }

    fun deleteAllCacheAndRoom() {
        deleteAllJob = vmScope.launch(ioDispatcher) {
            deleteAllUseCase.execute()
            updateUiResource(Resource.Success(emptyList())) // to show msg "List is empty. Swipe down.."
        }
    }

    fun deleteAllCache() {
        deleteAllCacheJob = vmScope.launch(ioDispatcher) {
            deleteAllCacheUseCase.execute()
            updateUiResource(Resource.Success(emptyList())) // to show msg "List is empty. Swipe down.."
        }
    }

    fun refreshDatas() {
        refreshJob = vmScope.launch(ioDispatcher) {
            deleteAllUseCase.execute()
            fetchDatasFromUseCase()
        }
    }

    fun fetchNextDatas() {
        nextDatasJob = vmScope.launch(ioDispatcher) {
            //TODO create real FetchNextDatasUseCase
            // with pageCount & logic or Android Paging3.
            // For now, we delete all and get same page
            deleteAllUseCase.execute()
            fetchDatasFromUseCase()
        }
    }

    @WorkerThread
    private suspend fun fetchDatasFromUseCase() {
        DebugHelp.l("fetchDatasFromUseCase")
        updateUiLoading()
        wrapEspressoIdlingResource {
            try {
                val resultResource = fetchUsersUseCase.execute()
                updateUiResource(resultResource)
            } catch (e: Exception) {
                DebugHelp.l("ERROR $e")
                updateUiError("$e")
            }
        }
    }
}