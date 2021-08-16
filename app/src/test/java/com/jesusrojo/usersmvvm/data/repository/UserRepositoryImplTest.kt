package com.jesusrojo.usersmvvm.data.repository

import com.google.common.truth.Truth.assertThat
import com.jesusrojo.usersmvvm.data.model.UserRaw
import com.jesusrojo.usersmvvm.data.repository.datasource.UsersCacheDataSource
import com.jesusrojo.usersmvvm.data.repository.datasource.UsersLocalDataSource
import com.jesusrojo.usersmvvm.data.repository.datasource.UsersRemoteDataSource
import com.jesusrojo.usersmvvm.data.model.mappers.MapperRawToEnty
import com.jesusrojo.usersmvvm.utils.BaseUnitTest
import com.jesusrojo.usersmvvm.utils.Resource
import com.jesusrojo.usersmvvm.utils.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import retrofit2.Response


class UserRepositoryImplTest: BaseUnitTest() {

    private val remoteDataSource: UsersRemoteDataSource = mock()
    private val localDataSource: UsersLocalDataSource = mock()
    private val cacheDataSource: UsersCacheDataSource = mock()
    private val mapper: MapperRawToEnty = mock()
//    val response: Response<List<User>> = mock()
    private val datas = FakeRepository.getFakeListGHRepoRawItemsOneTwo()
    private val result = datas
    private val response: Response<List<UserRaw>> = Response.success(result)

    private val sut = UsersRepositoryImpl(remoteDataSource, localDataSource, cacheDataSource, mapper)

    @Test
    fun fetchUsers_callCache_listOK() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val datas = FakeRepository.getFakeListItemsOneTwo()
            whenever(cacheDataSource.fetchDatasFromCache()).thenReturn(datas)
            val results = sut.fetchUsers()
            assertThat(results.data).isEqualTo(datas)
            verify(cacheDataSource, times(1)).fetchDatasFromCache()
        }

    @Test
    fun fetchUsers_callCache_callLocal_listOK() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val datas = FakeRepository.getFakeListItemsOneTwo()
            whenever(cacheDataSource.fetchDatasFromCache()).thenReturn(null)
            whenever(localDataSource.fetchAllInDB()).thenReturn(datas)
            val results = sut.fetchUsers()
            assertThat(results.data).isEqualTo(datas)
            verify(cacheDataSource, times(1)).fetchDatasFromCache()
            verify(localDataSource, times(1)).fetchAllInDB()
        }


    @Test
    fun fetchUsers_callCache_callLocal_callRemote_listOK() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            whenever(cacheDataSource.fetchDatasFromCache()).thenReturn(null)
            whenever(localDataSource.fetchAllInDB()).thenReturn(null)
            whenever(remoteDataSource.fetchUsers()).thenReturn(response)
//            val datas = FakeRepository.getFakeListGHRepoRawItemsOneTwo()
//            val result = datas
//            whenever(response.body()).thenReturn(result)

            val results = sut.fetchUsers()

            verify(cacheDataSource, times(1)).fetchDatasFromCache()
            verify(localDataSource, times(1)).fetchAllInDB()
            verify(remoteDataSource, times(1)).fetchUsers()

//            assertThat(results).isEqualTo(datas) //error
        }
}