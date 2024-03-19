package com.antareza.tesdanamon.data.reqres

import android.annotation.SuppressLint
import com.antareza.tesdanamon.data.reqres.local.db.UserDao
import com.antareza.tesdanamon.data.reqres.model.User
import com.antareza.tesdanamon.data.reqres.model.UserEntity
import com.antareza.tesdanamon.data.reqres.web.PhotoApi
import com.antareza.tesdanamon.domain.reqres.model.Photo
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class ManageDataStore(
    private val api: PhotoApi,
    private val db: UserDao
) : ManageRepository {
    override fun getPhotos(page: Int): Flowable<List<Photo>> =
        api.getPhotos(page, 10, "albumId")

    @SuppressLint("CheckResult")
    override fun insertUser(user: User): Flowable<Unit> {
        return Flowable.create({ emitter ->
            Single.zip(
                db.getUserByUsername(user.username).subscribeOn(Schedulers.io()),
                db.getUserByEmail(user.email).subscribeOn(Schedulers.io()),
            ) { existingUserByUsername, existingUserByEmail ->
                (existingUserByUsername.isEmpty() && existingUserByEmail.isEmpty())
            }.flatMap {
                if (it) {
                    db.insertUser(user.toUserEntity())
                } else {
                    Single.just(-1L)
                }
            }.subscribe({
                if (it != -1L) {
                    emitter.onNext(Unit)
                    emitter.onComplete()
                } else {
                    emitter.onError(Exception("Username or Email already exists"))
                }
            }, {
                emitter.onError(it)
            })
        }, BackpressureStrategy.LATEST)
    }

    @SuppressLint("CheckResult")
    override fun getAllUsers(): Flowable<List<User>> {
        return Flowable.create(
            { emitter ->
                db.getAllUsers()
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        emitter.onNext(it.map { userEntity -> userEntity.toUser() })
                        emitter.onComplete()
                    }, {
                        emitter.onError(it)
                    })
            }, BackpressureStrategy.LATEST
        )
    }

    @SuppressLint("CheckResult")
    override fun getUserByEmail(email: String): Single<User> {
        return db.getUserByEmail(email).map { it[0].toUser() }
    }

    @SuppressLint("CheckResult")
    override fun getUserByUsername(username: String): Single<User> {
        return db.getUserByUsername(username).map { it[0].toUser() }
    }

    @SuppressLint("CheckResult")
    override fun getUserByEmailAndPassword(email: String, password: String): Single<User> {
        return db.getUserByEmailAndPassword(email, password).map { it.toUser() }
    }

    @SuppressLint("CheckResult")
    override fun updateUser(user: UserEntity): Flowable<Result<Unit>> {
        return Flowable.create({ emitter ->
            db.updateUser(user)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    emitter.onNext(Result.success(Unit))
                    emitter.onComplete()
                }, {
                    emitter.onError(it)
                })
        }, BackpressureStrategy.LATEST)
    }

    @SuppressLint("CheckResult")
    override fun deleteUserById(userId: Int): Flowable<Result<Unit>> {
        return Flowable.create({ emitter ->
            db.deleteUserById(userId)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    emitter.onNext(Result.success(Unit))
                    emitter.onComplete()
                }, {
                    emitter.onError(it)
                })
        }, BackpressureStrategy.LATEST)
    }
}