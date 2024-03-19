package com.antareza.tesdanamon.presentation.useredit

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.antareza.tesdanamon.data.reqres.ManageRepository
import com.antareza.tesdanamon.data.reqres.model.UserEntity

class AdminEditUserViewModel(
    private val repository: ManageRepository
) : ViewModel() {

    val isEditDataSuccess: MutableLiveData<Boolean> by lazy { MutableLiveData() }

    @SuppressLint("CheckResult")
    fun editData(user: UserEntity) {
        repository.updateUser(user)
            .subscribe({
                isEditDataSuccess.postValue(true)
            }, {
                isEditDataSuccess.postValue(false)
            }).let {

            }

    }
}