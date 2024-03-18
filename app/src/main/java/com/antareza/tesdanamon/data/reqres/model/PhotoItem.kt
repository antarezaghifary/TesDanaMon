package com.antareza.tesdanamon.data.reqres.model

import com.antareza.tesdanamon.domain.reqres.model.Photo
import com.google.gson.annotations.SerializedName


data class PhotoItem(
    @SerializedName("albumId")
    val albumId: Int,

    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String,
){
    fun toPhoto() = Photo(
        albumId = albumId,
        id = id,
        title = title,
        url = url,
        thumbnailUrl = thumbnailUrl
    )
}