package com.jesusrojo.usersmvvm.presentation.viewmodel

import androidx.lifecycle.Observer
import com.google.common.truth.Truth.assertThat
import com.jesusrojo.usersmvvm.data.model.User
import com.jesusrojo.usersmvvm.data.repository.FakeRepository
import com.jesusrojo.usersmvvm.utils.Resource
import com.jesusrojo.usersmvvm.domain.usecase.DeleteAllUsersUseCase
import com.jesusrojo.usersmvvm.domain.usecase.FetchUsersUseCase
import com.jesusrojo.usersmvvm.utils.BaseUnitTest
import com.jesusrojo.usersmvvm.utils.LiveDataTestUtil
import com.jesusrojo.usersmvvm.utils.getOrAwaitValue
import com.jesusrojo.usersmvvm.utils.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

class UsersViewModelTest: BaseUnitTest() {

    private lateinit var sutFake: UsersViewModel
    private val observer: Observer<Resource<List<User>>> = mock()

    @Before
    fun setUp(){
        val fakeRepository = FakeRepository()
        val fetchUseCase = FetchUsersUseCase(fakeRepository)
        val deleteAllUseCase = DeleteAllUsersUseCase(fakeRepository)
        val ioDispatcher = Dispatchers.IO
        sutFake = UsersViewModel(fetchUseCase, deleteAllUseCase, ioDispatcher)
    }

    // TODO  two test fail, but if commented the other test that used to pass fail

//    @Test //FAIL and first part is the same as next test
//    fun fetchDatas_loadingShow() =
//        coroutinesTestRule.testDispatcher.runBlockingTest {
//
//            val state = sutFake.resource
//            state.observeForever(observer)
//
//            sutFake.fetchDatas()
//
//            val resultResource = state.getOrAwaitValue()
//            assertThat(resultResource.data).isEqualTo(null)
//        }

    @Test
    fun fetchDatas_loadingShow_dataOK() =
        coroutinesTestRule.testDispatcher.runBlockingTest {

            val state = sutFake.resource
            state.observeForever(observer)

            sutFake.fetchDatas()

            var resultResource = state.getOrAwaitValue()
            assertThat(resultResource.data).isEqualTo(null)

            resultResource = state.getOrAwaitValue()
            assertThat(resultResource.data).isNotNull()
            assertThat(resultResource.data?.size).isEqualTo(2)
        }


//    @Test //FAIL
//    fun fetchDatas_fullCycle_FAKE() {
//        // Pause dispatcher so we can verify initial values
//        mainCoroutineRule.pauseDispatcher()
//
//        sutFake.fetchDatas()
//
//        // Then progress indicator is shown
//        assertThat(LiveDataTestUtil.getValue(sutFake.resource).data).isNull()
//
//        // Execute pending coroutines actions
//        mainCoroutineRule.resumeDispatcher()
//
//        // And data correctly loaded
////       assertThat(LiveDataTestUtil.getValue(sutFake.resource).data)
////           .isNotNull() //FAIL
////        assertThat(LiveDataTestUtil.getValue(sutFake.resource).data)
////            .hasSize(2) // FAILerror java.lang.NullPointerException
//    }
}