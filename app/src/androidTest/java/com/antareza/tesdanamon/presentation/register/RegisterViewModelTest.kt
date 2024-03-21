package com.antareza.tesdanamon.presentation.register

import com.antareza.tesdanamon.data.reqres.MockManageRepository
import com.antareza.tesdanamon.data.reqres.model.User
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test

class RegisterViewModelTest {
    private lateinit var viewModel: RegisterViewModel

    @RelaxedMockK
    @MockK
    private lateinit var manageRepository: MockManageRepository
    private lateinit var testScheduler: TestScheduler

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        if (this::manageRepository.isInitialized) manageRepository = MockManageRepository()
        testScheduler = TestScheduler()
        viewModel = RegisterViewModel(manageRepository)

    }

    @After
    fun tearDown() {

    }

    @Test
    fun register() {
//        coEvery { manageRepository.insertUser(any()) } returns Flowable.just(Unit)
        val user = User(
            id = 1,
            email = "email@mail.com",
            username = "email",
            password = "123456",
            role = 1,
        )


        // When
        val testObserver = manageRepository.insertUser(user).test()
        viewModel.register(user)
//        viewModel.isSuccessRegister.observeForever {  }

        // Then
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValues(Unit)
    }

    @Test
    fun isSuccessRegister() {

    }
}
