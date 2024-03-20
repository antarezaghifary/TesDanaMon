package com.antareza.tesdanamon.data.reqres.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    @SerializedName("id") var id: Int = 0,
    @SerializedName("username") var username: String = "",
    @SerializedName("email") var email: String = "",
    @SerializedName("password") var password: String = "",
    @SerializedName("role") var role: Int = 0,
) : Parcelable {
    fun toUserEntity(): UserEntity {
        return UserEntity(id, username, email, password, role)
    }
}