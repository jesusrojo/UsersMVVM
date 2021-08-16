package com.jesusrojo.usersmvvm.presentation.di.hilt

import com.jesusrojo.usersmvvm.domain.usecase.*
import com.jesusrojo.usersmvvm.presentation.viewmodel.UsersViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelFactoryModule {

    @Singleton
    @Provides
    fun provideMyViewModelFactory(
            fetchUsersUseCase: FetchUsersUseCase,
            deleteAllUsersUseCase: DeleteAllUsersUseCase,
            @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): UsersViewModelFactory {
        return UsersViewModelFactory(
            fetchUsersUseCase,
            deleteAllUsersUseCase,
            ioDispatcher
        )
    }
}