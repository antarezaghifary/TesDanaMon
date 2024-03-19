package com.antareza.tesdanamon.di

import com.antareza.tesdanamon.data.reqres.ManageDataStore
import com.antareza.tesdanamon.data.reqres.ManageRepository
import com.antareza.tesdanamon.presentation.admin.AdminViewModel
import com.antareza.tesdanamon.presentation.login.LoginViewModel
import com.antareza.tesdanamon.presentation.register.RegisterViewModel
import com.antareza.tesdanamon.presentation.user.UserViewModel
import com.antareza.tesdanamon.presentation.useredit.AdminEditUserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val reqresModule = module {
    single<ManageRepository> {
        ManageDataStore(
            get(),
            get()
        )
    }
    viewModel { UserViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { AdminViewModel(get()) }
    viewModel { AdminEditUserViewModel(get()) }
}