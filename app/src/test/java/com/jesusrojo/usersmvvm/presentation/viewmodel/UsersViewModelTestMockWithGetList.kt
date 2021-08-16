//package com.jesusrojo.usersmvvm.presentation.viewmodel
//
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.Observer
//import com.jesusrojo.usersmvvm.data.model.User
//import com.jesusrojo.usersmvvm.domain.usecase.DeleteAllUsersUseCase
//import com.jesusrojo.usersmvvm.domain.usecase.FetchUsersUseCase
//import com.jesusrojo.usersmvvm.utils.Resource
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.test.TestCoroutineDispatcher
//import kotlinx.coroutines.test.resetMain
//import kotlinx.coroutines.test.runBlockingTest
//import kotlinx.coroutines.test.setMain
//import org.hamcrest.CoreMatchers.equalTo
//import org.hamcrest.MatcherAssert.assertThat
//import org.junit.After
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.junit.runners.JUnit4
//import org.mockito.Mock
//import org.mockito.MockitoAnnotations
//
//
//// https://github.com/lucaslabs/TheNewsApp/blob/master/app/src/test/java/com/thenewsapp/presentation/shownews/SharedNewsViewModelTest.kt
//@RunWith(JUnit4::class)
//@ExperimentalCoroutinesApi
//class UsersViewModelTestMockWithGetList {
//
//    // This rule allows us to run LiveData synchronously
//    @get:Rule
//    val instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    private val testCoroutineDispatcher = TestCoroutineDispatcher()
//
//    @Mock lateinit var observer: Observer<Resource<*>>
//
//    @Mock lateinit var fetchUseCaseMock: FetchUsersUseCase
//    @Mock lateinit var deleteAllUseCaseMock: DeleteAllUsersUseCase
//    private val ioDispatcher = Dispatchers.IO
//    @Mock lateinit var runtimeException: RuntimeException
//
//    private lateinit var sutMock: UsersViewModel
//
//    @Before
//    fun setup() {
//        MockitoAnnotations.initMocks(this)
//        sutMock = UsersViewModel(fetchUseCaseMock, deleteAllUseCaseMock, ioDispatcher)
//
//        // Observe the LiveData forever
//        sutMock.resource.observeForever(observer)
//
//        // Sets the given [dispatcher] as an underlying dispatcher of [Dispatchers.Main]
//        Dispatchers.setMain(testCoroutineDispatcher)
//    }
//
//    @After
//    fun tearDown() {
//        // Whatever happens, remove the observer!
//        sutMock.resource.removeObserver(observer)
//
//        // Resets state of the [Dispatchers.Main] to the original main dispatcher
//        Dispatchers.resetMain()
//
//        // Clean up the TestCoroutineDispatcher to make sure no other work is running
//        testCoroutineDispatcher.cleanupTestCoroutines()
//    }
//
//
//    suspend fun foo() {
//        delay(1000)
//        println("foo")
//    }
//
//    @Test
//    fun `test delay in coroutine`() = runBlockingTest {
//        val realStartTime = System.currentTimeMillis()
//        val virtualStartTime = currentTime
//
//        foo()
//
//        println("Unit test time: ${System.currentTimeMillis() - realStartTime} ms")
//        println("Real call time: ${currentTime - virtualStartTime} ms")
//    }
//
//    @Test
//    fun `test live data_success`() {
//        // Given
//        val mutableLiveData = MutableLiveData<Resource<List<User>>>()
//
//        // When
//        mutableLiveData.value = Resource.Success(emptyList())
//
//        // Then
//        assertThat(mutableLiveData.value?.data, equalTo(emptyList()))
//    }
//
//    @Test
//    fun `test live data_loading`() {
//        // Given
//        val mutableLiveData = MutableLiveData<Resource<List<User>>>()
//
//        // When
//        mutableLiveData.value = Resource.Loading(null)
//
//        // Then
//        assertThat(mutableLiveData.value?.data, equalTo(null))
//    }
//
//    @Test
//    fun `test live data_error`() {
//        // Given
//        val errorMessage = "Error testing"
//        val mutableLiveData = MutableLiveData<Resource<List<User>>>()
//
//        // When
//        mutableLiveData.value = Resource.Error(errorMessage)
//
//        // Then
//        assertThat(mutableLiveData.value?.data, equalTo(null))
//        assertThat(mutableLiveData.value?.message, equalTo(errorMessage))
//    }
//}