package com.antareza.tesdanamon.presentation.user

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.antareza.tesdanamon.domain.reqres.model.Photo
import com.antareza.tesdanamon.util.VmData
import com.oratakashi.viewbinding.core.tools.State


class UserObserver(
    private val view: UserDataContract,
    private val viewModel: UserViewModel
) : DefaultLifecycleObserver {
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        viewModel.photos.observe(owner) {
            when (it) {

                is State.Default<*> -> Unit
                is State.Loading<*> -> view.onGetPhotoLoading()
                is State.Success<*> -> {
                    view.onGetPhotoSuccess(it.data as List<Photo>)
                }

                is State.Failure<*> -> view.onGetPhotoFailure(it.throwable!!)
                is State.Empty<*> -> view.onGetPhotoEmpty()
            }
        }
    }
}