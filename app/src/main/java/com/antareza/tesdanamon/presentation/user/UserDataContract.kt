package com.antareza.tesdanamon.presentation.user

import com.antareza.tesdanamon.domain.reqres.model.Photo
import com.oratakashi.viewbinding.core.tools.State

interface UserDataContract {

    fun onGetPhotoLoading()
    fun onGetPhotoSuccess(data: List<Photo>)
    fun onGetPhotoEmpty()
    fun onGetPhotoFailure(error: Throwable)

}