package com.jesusrojo.usersmvvm.data.repository.datasourceimpl

import com.google.common.truth.Truth.assertThat
import com.jesusrojo.usersmvvm.data.db.UsersDAO
import com.jesusrojo.usersmvvm.data.model.User
import com.jesusrojo.usersmvvm.data.repository.FakeRepository
import com.jesusrojo.usersmvvm.utils.BaseUnitTest
import com.jesusrojo.usersmvvm.utils.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.Mockito.times


class UsersLocalDataSourceImplTest: BaseUnitTest() {

    private val myDAO: UsersDAO = mock()
    private val sut = UsersLocalDataSourceImpl(myDAO)

    @Test
    fun fetchAllInDB_getEmptyList_And_callDAO()  =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val results = sut.fetchAllInDB()
            assertThat(results).isEqualTo(emptyList<User>())
            verify(myDAO, times(1)).fetchAll()
        }

    @Test
    fun fetchAllInDB_callDAO()  =
        coroutinesTestRule.testDispatcher.runBlockingTest {

            sut.fetchAllInDB()
            verify(myDAO, times(1)).fetchAll()
        }

    @Test
    fun fetchAllInDB_returnFakeListMocked()  =
        coroutinesTestRule.testDispatcher.runBlockingTest {

            val datas = FakeRepository.getFakeListItemsOneTwo()
            whenever(myDAO.fetchAll()).thenReturn(datas)

            val results = sut.fetchAllInDB()
            assertThat(results).isEqualTo(datas)
        }
}