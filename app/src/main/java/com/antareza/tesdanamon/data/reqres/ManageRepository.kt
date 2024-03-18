package com.antareza.tesdanamon.data.reqres

import com.antareza.tesdanamon.data.reqres.model.User
import com.antareza.tesdanamon.data.reqres.model.UserEntity
import com.antareza.tesdanamon.domain.reqres.model.Photo
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface ManageRepository {
    fun getPhotos(page: Int, limit: Int): Flowable<List<Photo>>
    fun insertUser(user: User): Flowable<Unit>
    fun getAllUsers(): Flowable<List<User>>
    fun getUserByEmail(email: String): Single<User>
    fun getUserByUsername(username: String): Single<User>
    fun getUserByEmailAndPassword(email: String, password: String): Single<User>
    suspend fun updateUser(user: UserEntity): Flowable<Result<Unit>>
    fun deleteUserById(userId: Int): Flowable<Result<Unit>>
}