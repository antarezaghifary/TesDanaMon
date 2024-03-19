package com.antareza.tesdanamon.data.reqres.web

import com.antareza.tesdanamon.domain.reqres.model.Photo
import io.reactivex.rxjava3.core.Flowable

class PhotoApi(
    private val api: PhotoApiClient
) : PhotoApiClient {
    override fun getPhotos(page: Int, limit: Int, sort: String): Flowable<List<Photo>> {
        return api.getPhotos(page, 10, "albumId")
    }
}