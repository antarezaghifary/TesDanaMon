package com.antareza.tesdanamon.presentation.login

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.antareza.tesdanamon.data.reqres.ManageRepository
import com.antareza.tesdanamon.data.reqres.model.User

class LoginViewModel(
    private val repository: ManageRepository
) : ViewModel() {

    val isSuccessLogin: MutableLiveData<User> by lazy { MutableLiveData() }

    @SuppressLint("CheckResult")
    fun login(email: String, password: String) {
        repository.getUserByEmailAndPassword(email, password)
            .subscribe({ user ->
                isSuccessLogin.postValue(user)
            }, {
                isSuccessLogin.postValue(null)
            }).let {
                return@let it
            }
    }
}