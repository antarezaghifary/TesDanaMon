package com.antareza.tesdanamon.data.reqres

import com.antareza.tesdanamon.data.reqres.model.User
import com.antareza.tesdanamon.data.reqres.model.UserEntity
import com.antareza.tesdanamon.domain.reqres.model.Photo
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

class MockManageRepository: ManageRepository {
    override fun getPhotos(page: Int): Flowable<List<Photo>> {
        TODO("Not yet implemented")
    }

    override fun insertUser(user: User): Flowable<Unit> {
        return Flowable.just(Unit)
    }

    override fun getAllUsers(): Flowable<List<User>> {
        TODO("Not yet implemented")
    }

    override fun getUserByEmail(email: String): Single<User> {
        TODO("Not yet implemented")
    }

    override fun getUserByUsername(username: String): Single<User> {
        TODO("Not yet implemented")
    }

    override fun getUserByEmailAndPassword(email: String, password: String): Single<User> {
        TODO("Not yet implemented")
    }

    override fun updateUser(user: UserEntity): Flowable<Result<Unit>> {
        TODO("Not yet implemented")
    }

    override fun deleteUserById(userId: Int): Flowable<Result<Unit>> {
        TODO("Not yet implemented")
    }
}