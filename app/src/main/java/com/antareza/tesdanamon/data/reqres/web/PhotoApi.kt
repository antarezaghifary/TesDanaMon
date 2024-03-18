package com.antareza.tesdanamon.data.reqres.web

class PhotoApi(
    private val api: PhotoApiClient
) : PhotoApiClient {
    override fun getPhotos(page: Int, limit: Int) = api.getPhotos(page, limit)
}