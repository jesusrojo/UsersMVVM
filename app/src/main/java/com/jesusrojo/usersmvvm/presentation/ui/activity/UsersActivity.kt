package com.jesusrojo.usersmvvm.presentation.ui.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.jesusrojo.usersmvvm.utils.Resource
import com.jesusrojo.usersmvvm.presentation.viewmodel.UsersViewModel
import com.jesusrojo.usersmvvm.presentation.viewmodel.UsersViewModelFactory
import com.jesusrojo.usersmvvm.utils.DebugHelp
import dagger.hilt.android.AndroidEntryPoint
import com.jesusrojo.usersmvvm.utils.exhaustive
import javax.inject.Inject

@AndroidEntryPoint
class UsersActivity : BaseUiActivity() {

    @Inject lateinit var viewModelFactory: UsersViewModelFactory
    private lateinit var viewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(UsersViewModel::class.java)

        observeViewModel()

        if (savedInstanceState == null){
            viewModel.fetchDatas()
        }
    }

    private fun observeViewModel() {
        viewModel.resource.observe(this, { state ->
            DebugHelp.l("observeViewModel")
            when (state) {
                is Resource.Success -> updateUiSuccess(state.data)
                is Resource.Error -> updateUiError(state.message)
                is Resource.Loading -> updateUiLoading()
            }.exhaustive
        })
    }

    override fun refreshDatas() {
        viewModel.refreshDatas()
    }

    override fun fetchNextDatas() {
        viewModel.fetchNextDatas()
    }

    override fun deleteAllCacheAndRoom() {
        viewModel.deleteAllCacheAndRoom()
    }

    override fun deleteAllCache() {
        viewModel.deleteAllCache()
    }
}