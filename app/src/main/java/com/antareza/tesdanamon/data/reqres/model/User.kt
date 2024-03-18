package com.antareza.tesdanamon.data.reqres.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("username") val username: String = "",
    @SerializedName("email") val email: String = "",
    @SerializedName("password") val password: String = "",
    @SerializedName("role") val role: Int = 0,
    @SerializedName("success") val success: Boolean = false
) : Parcelable {
    fun toUserEntity(): UserEntity {
        return UserEntity(id, username, email, password, role, success)
    }
}