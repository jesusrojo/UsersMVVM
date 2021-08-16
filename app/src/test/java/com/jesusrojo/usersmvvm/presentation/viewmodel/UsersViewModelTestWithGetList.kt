//package com.jesusrojo.usersmvvm.presentation.viewmodel
//
//import androidx.lifecycle.Observer
//import com.google.common.truth.Truth.assertThat
//import com.jesusrojo.usersmvvm.data.model.User
//import com.jesusrojo.usersmvvm.data.repository.FakeRepository
//import com.jesusrojo.usersmvvm.utils.Resource
//import com.jesusrojo.usersmvvm.domain.usecase.DeleteAllUsersUseCase
//import com.jesusrojo.usersmvvm.domain.usecase.FetchUsersUseCase
//import com.jesusrojo.usersmvvm.utils.BaseUnitTest
//import com.jesusrojo.usersmvvm.utils.LiveDataTestUtil
//import com.jesusrojo.usersmvvm.utils.getOrAwaitValue
//import com.jesusrojo.usersmvvm.utils.mock
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.test.runBlockingTest
//import org.junit.Before
//import org.junit.Test
//
//class UsersViewModelTestWithGetList: BaseUnitTest() {
//
//    private lateinit var sutFake: UsersViewModel
//    private val observer: Observer<Resource<List<User>>> = mock()
//
//    @Before
//    fun setUp(){
//        val fakeRepository = FakeRepository()
//        val fetchUseCase = FetchUsersUseCase(fakeRepository)
//        val deleteAllUseCase = DeleteAllUsersUseCase(fakeRepository)
//        val ioDispatcher = Dispatchers.IO
//        sutFake = UsersViewModel(fetchUseCase, deleteAllUseCase, ioDispatcher)
//    }
//
//    // TODO  two test fail, but if commented the other test that used to pass fail
//
//    @Test //FAIL
//    fun fetchDatas_loadingShow() =
//        coroutinesTestRule.testDispatcher.runBlockingTest {
//
//            val state = sutFake.resource
//            state.observeForever(observer)
//
//            sutFake.fetchDatas()
//
//            val results = state.getOrAwaitValue()
//            val datas = results.data
//
//            assertThat(datas).isEqualTo(null)
//        }
//
//    @Test
//    fun fetchDatas_loadingShow_dataOK() =
//        coroutinesTestRule.testDispatcher.runBlockingTest {
//
//            val state = sutFake.resource
//            state.observeForever(observer)
//
//            sutFake.fetchDatas()
//
//            var results = state.getOrAwaitValue()
//            var datas = results.data
//            assertThat(datas).isEqualTo(null)
//
//            results = state.getOrAwaitValue()
//            datas = results.data
//            assertThat(datas?.size).isEqualTo(2)
//        }
//
//
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
//       assertThat(LiveDataTestUtil.getValue(sutFake.resource).data).hasSize(2)
//    }
//}