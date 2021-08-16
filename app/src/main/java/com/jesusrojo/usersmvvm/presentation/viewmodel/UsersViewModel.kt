package com.jesusrojo.usersmvvm.presentation.viewmodel

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jesusrojo.usersmvvm.data.model.User
import com.jesusrojo.usersmvvm.utils.DebugHelp
import com.jesusrojo.usersmvvm.utils.Resource
import com.jesusrojo.usersmvvm.utils.tests.wrapEspressoIdlingResource
import com.jesusrojo.usersmvvm.domain.usecase.DeleteAllUsersUseCase
import com.jesusrojo.usersmvvm.domain.usecase.FetchUsersUseCase
import kotlinx.coroutines.*
import javax.inject.Inject


class UsersViewModel @Inject constructor(
    fetchUsersUseCase: FetchUsersUseCase,
    deleteAllUsersUseCase: DeleteAllUsersUseCase,
    ioDispatcher: CoroutineDispatcher
) : BaseUsersViewModel(
    fetchUsersUseCase,
    deleteAllUsersUseCase,
    ioDispatcher
) {

    private val _resource: MutableLiveData<Resource<List<User>>> = MutableLiveData()
    val resource: LiveData<Resource<List<User>>> = _resource

    private fun updateUiLoading() {
        _resource.postValue(Resource.Loading())
    }

    private fun updateUiError(message: String) {
        _resource.postValue(Resource.Error(message))
    }

    private fun updateUiSuccess(users: List<User>) {
        _resource.postValue(Resource.Success(users))
    }

    init {
        DebugHelp.l("init Resource viewModel")
    }

    //USE CASES
    fun fetchDatas() {
        datasJob = vmScope.launch(ioDispatcher) {
            fetchDatasFromUseCase()
        }
    }

    fun deleteAllCacheAndRoom() {
        deleteJob = vmScope.launch(ioDispatcher) {
            deleteAllUsersUseCase.execute()
            updateUiSuccess(emptyList()) // to show msg "List is empty. Swipe down.."
        }
    }

    fun refreshDatas() {
        refreshJob = vmScope.launch(ioDispatcher) {
            deleteAllUsersUseCase.execute()
            fetchDatasFromUseCase()
        }
    }

    fun fetchNextDatas() {
        nextDatasJob = vmScope.launch(ioDispatcher) {
            //TODO create real FetchNextDatasUseCase
            // with pageCount & logic or Android Paging3.
            // For now, we delete all and get same page
            deleteAllUsersUseCase.execute()
            fetchDatasFromUseCase()
        }
    }

    @WorkerThread
    private suspend fun fetchDatasFromUseCase() {
        DebugHelp.l("fetchDatasFromUseCase")

        updateUiLoading()

        wrapEspressoIdlingResource {

            try {
                val datas: List<User>? = fetchUsersUseCase.execute()
                if (datas != null && datas.isNotEmpty()) {
                    updateUiSuccess(datas)
                } else {
                    updateUiError("Error: datas null or empty")
                }
            } catch (e: Exception) {
                DebugHelp.l("ERROR $e")
                updateUiError("$e")
            }

        }
    }
}