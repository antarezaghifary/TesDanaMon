package com.antareza.tesdanamon.presentation.register

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.antareza.tesdanamon.data.reqres.ManageRepository
import com.antareza.tesdanamon.data.reqres.model.User

class RegisterViewModel(
    private val repository: ManageRepository
): ViewModel(){

    val isSuccessRegister: MutableLiveData<Boolean> by lazy { MutableLiveData() }

    @SuppressLint("CheckResult")
    fun register(user: User){
        repository.insertUser(user)
            .subscribe({
                isSuccessRegister.postValue(true)
            },{
                isSuccessRegister.postValue(false)
            })
    }
}