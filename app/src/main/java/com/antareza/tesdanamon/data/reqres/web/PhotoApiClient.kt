package com.antareza.tesdanamon.data.reqres.web

import com.antareza.tesdanamon.data.reqres.model.PhotoItem
import com.antareza.tesdanamon.domain.reqres.model.Photo
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApiClient {
    @GET("photos")
    fun getPhotos(
        @Query("_page") page: Int,
        @Query("_limit") limit: Int,
    ): Flowable<List<Photo>>
}