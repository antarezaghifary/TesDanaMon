package com.antareza.tesdanamon.presentation.register

import com.antareza.tesdanamon.data.reqres.MockManageRepository
import com.antareza.tesdanamon.data.reqres.model.User
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class RegisterViewModelTest {

    private lateinit var viewModel: RegisterViewModel
    private lateinit var manageRepository: MockManageRepository
    private lateinit var testScheduler: TestScheduler

    @Before
    fun setUp() {
        testScheduler = TestScheduler()
        MockKAnnotations.init(this)
        MockKAnnotations.init(testScheduler)
        MockitoAnnotations.openMocks(this)
        viewModel = RegisterViewModel(manageRepository)

    }

    @After
    fun tearDown() {

    }

    @Test
    fun register() {
        coEvery { manageRepository.insertUser(any()) } returns Flowable.just(Unit)
        val user = User(
            id = 1,
            email = "antareza@mail.com",
            username = "antareza",
            password = "123456",
            role = 1,
        )
//        `when`(manageRepository.insertUser(any())).thenReturn(Flowable.just(Unit))

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
