package com.antareza.tesdanamon.presentation.register

import com.antareza.tesdanamon.data.reqres.ManageRepository
import com.antareza.tesdanamon.data.reqres.MockManageRepository
import com.antareza.tesdanamon.data.reqres.model.User
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class RegisterViewModelTest {
    private lateinit var viewModel: RegisterViewModel
    private lateinit var manageRepository: ManageRepository
    private lateinit var testScheduler: TestScheduler

    @Before
    fun setUp() {
        if (this::manageRepository.isInitialized) manageRepository = MockManageRepository()
        manageRepository = MockManageRepository()
//        manageRepository = mock(ManageRepository::class.java)
        testScheduler = TestScheduler()
        MockitoAnnotations.openMocks(this)
        viewModel = RegisterViewModel(manageRepository)

    }

    @After
    fun tearDown() {

    }

    @Test
    fun register() {
        val user = User(
            id = 1,
            email = "email@mail.com",
            username = "email",
            password = "123456",
            role = 1,
        )
        `when`(manageRepository.insertUser(user)).thenReturn(Flowable.just(Unit))

//        every { manageRepository.insertUser(user) } returns Flowable.just(Unit)

        // When
        val testObserver = manageRepository.insertUser(user).test()

        // Then
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValues(Unit)
    }

    @Test
    fun isSuccessRegister() {
    }
}
