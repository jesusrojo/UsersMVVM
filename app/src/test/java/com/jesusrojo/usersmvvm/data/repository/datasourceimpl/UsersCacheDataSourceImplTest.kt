package com.jesusrojo.usersmvvm.data.repository.datasourceimpl

import com.google.common.truth.Truth.assertThat
import com.jesusrojo.usersmvvm.data.model.User
import com.jesusrojo.usersmvvm.data.repository.FakeRepository
import com.jesusrojo.usersmvvm.utils.BaseUnitTest
import kotlinx.coroutines.test.runBlockingTest

import org.junit.Test


class UsersCacheDataSourceImplTest: BaseUnitTest() {

    private val sut = UsersCacheDataSourceImpl()

    @Test
    fun fetchUsersFromCache() =
        coroutinesTestRule.testDispatcher.runBlockingTest {

            val datas = emptyList<User>()
            sut.saveDatasToCache(datas)

            val results = sut.fetchDatasFromCache()
            assertThat(results).isEqualTo(datas)
        }

    @Test
    fun saveUsersToCache() =
        coroutinesTestRule.testDispatcher.runBlockingTest {

            val datas = FakeRepository.getFakeListItemsOneTwo()
            sut.saveDatasToCache(datas)

            val results = sut.fetchDatasFromCache()
            assertThat(results).isEqualTo(datas)
        }

    @Test
    fun deleteUsersFromCache() =
        coroutinesTestRule.testDispatcher.runBlockingTest {

            val datas = FakeRepository.getFakeListItemsOneTwo()
            sut.saveDatasToCache(datas)

            var results = sut.fetchDatasFromCache()
            assertThat(results).isEqualTo(datas)

            sut.deleteAllInCache()

            results = sut.fetchDatasFromCache()
            assertThat(results).isEqualTo(emptyList<User>())
        }
}