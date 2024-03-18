package com.antareza.tesdanamon.data.reqres.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val username: String,
    val email: String,
    val password: String,
    val role: Int,
    val success: Boolean
){
    fun toUser(): User {
        return User(id, username, email, password, role, success)
    }
}