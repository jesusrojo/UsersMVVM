package com.jesusrojo.usersmvvm.presentation.di.hilt

import com.jesusrojo.usersmvvm.domain.repository.UsersRepository
import com.jesusrojo.usersmvvm.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideFetchUseCase(
        repository: UsersRepository
    ): FetchUsersUseCase {
        return FetchUsersUseCase(repository)
    }


    @Singleton
    @Provides
    fun provideDeleteAllUseCase(
        repository: UsersRepository
    ): DeleteAllUseCase {
        return DeleteAllUseCase(repository)
    }
}