package com.antareza.tesdanamon.presentation.admin


import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.antareza.tesdanamon.data.reqres.ManageRepository
import com.antareza.tesdanamon.data.reqres.model.User
import com.antareza.tesdanamon.data.reqres.model.UserEntity

class AdminViewModel(
    private val repository: ManageRepository
) : ViewModel() {

    val isSuccessGetUser: MutableLiveData<Boolean> by lazy { MutableLiveData() }
    val userList: MutableLiveData<List<User>> by lazy { MutableLiveData() }
    val isDeleteUser: MutableLiveData<Boolean> by lazy { MutableLiveData() }


    @SuppressLint("CheckResult")
    fun getDataUser() {
        repository.getAllUsers()
            .subscribe({
                isSuccessGetUser.postValue(true)
                userList.postValue(it)
            }, {
                isSuccessGetUser.postValue(false)
                userList.postValue(null)
            })
    }

    @SuppressLint("CheckResult")
    fun deleteDataUser(user: Int) {
        repository.deleteUserById(user)
            .subscribe({
                isDeleteUser.postValue(true)
            }, {
                isDeleteUser.postValue(false)
            })
    }
}