package com.jesusrojo.usersmvvm.domain.usecase

import com.jesusrojo.usersmvvm.domain.repository.UsersRepository
import com.jesusrojo.usersmvvm.utils.BaseUnitTest
import com.jesusrojo.usersmvvm.utils.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class DeleteAllUsersUseCaseTest: BaseUnitTest() {

    private val repository: UsersRepository = mock()
    private val sut = DeleteAllUsersUseCase(repository)

    @Test
    fun execute_callRepository() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
        sut.execute()
        verify(repository, times(1)).deleteAll()
    }
}