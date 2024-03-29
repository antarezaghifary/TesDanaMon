package com.antareza.tesdanamon.presentation.user

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.antareza.tesdanamon.data.reqres.ManageRepository
import com.antareza.tesdanamon.domain.reqres.model.Photo
import com.antareza.tesdanamon.util.immutable
import com.oratakashi.viewbinding.core.binding.livedata.liveData
import com.oratakashi.viewbinding.core.tools.State
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class UserViewModel(
    private val repository: ManageRepository
) : ViewModel() {

    private val _photos: MutableLiveData<State<List<Photo>>> by liveData(State.default())
    val photos = _photos.immutable()
    val isLoading: MutableLiveData<Boolean> by liveData(false)
    val currenPage: MutableLiveData<Int> by liveData(1)

    @SuppressLint("CheckResult")
    fun getPhotos(page: Int) {
        _photos.postValue(State.loading())
        isLoading.postValue(true)
        page.let {
            currenPage.postValue(it)
        }
        repository.getPhotos(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                // Handle data
                _photos.postValue(State.success(data))
                isLoading.postValue(false)
            }, { error ->
                // Handle error
                _photos.postValue(State.fail(error, error.message ?: "Error"))
                isLoading.postValue(false)
            }).let {
                return@let it
            }
    }
}