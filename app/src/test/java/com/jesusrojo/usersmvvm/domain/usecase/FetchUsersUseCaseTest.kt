package com.jesusrojo.usersmvvm.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.jesusrojo.usersmvvm.data.repository.FakeRepository
import com.jesusrojo.usersmvvm.domain.repository.UsersRepository
import com.jesusrojo.usersmvvm.utils.BaseUnitTest
import com.jesusrojo.usersmvvm.utils.Resource
import com.jesusrojo.usersmvvm.utils.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test


class FetchUsersUseCaseTest: BaseUnitTest() {

    private val repository: UsersRepository = mock()
    private val sut = FetchUsersUseCase(repository)

    @Test
    fun execute_callRepository() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
        sut.execute()
        verify(repository, times(1)).fetchUsers()
    }

    @Test
    fun execute_callRepository_listOK() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val datas = FakeRepository.getFakeListItemsOneTwo()
            whenever(repository.fetchUsers()).thenReturn(Resource.Success(datas))
            val results = sut.execute()
            assertThat(results.data).isEqualTo(datas)
        }
}