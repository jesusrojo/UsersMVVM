package com.jesusrojo.usersmvvm.presentation.di.hilt


import com.jesusrojo.usersmvvm.data.api.UsersAPIService
import com.jesusrojo.usersmvvm.data.db.UsersDAO
import com.jesusrojo.usersmvvm.data.repository.UsersRepositoryImpl
import com.jesusrojo.usersmvvm.data.repository.datasource.UsersCacheDataSource
import com.jesusrojo.usersmvvm.data.repository.datasource.UsersLocalDataSource
import com.jesusrojo.usersmvvm.data.repository.datasource.UsersRemoteDataSource
import com.jesusrojo.usersmvvm.data.repository.datasourceimpl.UsersCacheDataSourceImpl
import com.jesusrojo.usersmvvm.data.repository.datasourceimpl.UsersLocalDataSourceImpl
import com.jesusrojo.usersmvvm.data.repository.datasourceimpl.UsersRemoteDataSourceImpl
import com.jesusrojo.usersmvvm.data.model.mappers.MapperRawToEnty
import com.jesusrojo.usersmvvm.domain.repository.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideUsersRepository(
        cacheDataSource: UsersCacheDataSource,
        localDataSource: UsersLocalDataSource,
        remoteDataSource: UsersRemoteDataSource,
        mapper: MapperRawToEnty
    ): UsersRepository {
        return UsersRepositoryImpl(
            remoteDataSource, localDataSource, cacheDataSource, mapper
        )
    }


    @Singleton
    @Provides
    fun provideRemoteDataSource(
        newsAPIService: UsersAPIService
    ): UsersRemoteDataSource {
        return UsersRemoteDataSourceImpl(newsAPIService)
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(myDAO: UsersDAO): UsersLocalDataSource {
        return UsersLocalDataSourceImpl(myDAO)
    }

    @Singleton
    @Provides
    fun provideCacheDataSource(): UsersCacheDataSource {
        return UsersCacheDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideMapperRawToEnty(): MapperRawToEnty {
        return MapperRawToEnty()
    }
}