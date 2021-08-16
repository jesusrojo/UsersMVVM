package com.jesusrojo.usersmvvm.data.repository.datasourceimpl

import com.google.common.truth.Truth.assertThat

import com.jesusrojo.usersmvvm.data.api.UsersAPIService
import com.jesusrojo.usersmvvm.data.model.UserRaw
import com.jesusrojo.usersmvvm.data.repository.FakeRepository
import com.jesusrojo.usersmvvm.utils.BaseUnitTest
import com.jesusrojo.usersmvvm.utils.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.mockito.Mockito.times
import retrofit2.Response

class UsersRemoteDataSourceImplTest: BaseUnitTest() {

    private val service: UsersAPIService = mock()
    private val sut = UsersRemoteDataSourceImpl(service)

    @Test
    fun fetchUsers_callService() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val response = sut.fetchUsers()
            verify(service, times(1)).fetchUsers()
    }

    @Test
    fun fetchUsers_callService_returnNull() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val response = sut.fetchUsers()
            verify(service, times(1)).fetchUsers()
            assertThat(response).isEqualTo(null)
    }

    @Test
    fun fetchUsers_withListEmptyMocked_isReturnedByService() =
        coroutinesTestRule.testDispatcher.runBlockingTest {

            val items: List<UserRaw> = emptyList()
            val body: List<UserRaw> = items
            val response: Response<List<UserRaw>> = Response.success(body)
            whenever(service.fetchUsers()).thenReturn(response)

            val responseResult = sut.fetchUsers()

            val bodyResult = responseResult.body()

            assertThat(bodyResult).isEqualTo(body)
            assertThat(bodyResult).isEqualTo(items)
            assertThat(bodyResult?.size).isEqualTo(0)
        }

    @Test
    fun fetchUsers_withListFakeMocked_isReturnedByService() =
        coroutinesTestRule.testDispatcher.runBlockingTest {

            val items: List<UserRaw> = FakeRepository.getFakeListGHRepoRawItemsOneTwo()
            val body: List<UserRaw> = items
            val response: Response<List<UserRaw>> = Response.success(body)
            whenever(service.fetchUsers()).thenReturn(response)

            val responseResult = sut.fetchUsers()

            val bodyResult = responseResult.body()

            assertThat(bodyResult).isEqualTo(body)
            assertThat(bodyResult).isEqualTo(items)
            assertThat(bodyResult?.size).isEqualTo(2)

            val dataRaw = bodyResult?.get(0)
            assertThat(dataRaw?.userName).isEqualTo("userName1")
        }
}