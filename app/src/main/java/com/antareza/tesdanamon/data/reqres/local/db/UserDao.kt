package com.antareza.tesdanamon.data.reqres.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.antareza.tesdanamon.data.reqres.model.UserEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserEntity): Single<Long>

    @Query("SELECT * FROM users")
    fun getAllUsers(): Single<List<UserEntity>>

    @Query("SELECT * FROM users WHERE email = :email")
    fun getUserByEmail(email: String): Single<List<UserEntity>>

    @Query("SELECT * FROM users WHERE username = :username")
    fun getUserByUsername(username: String): Single<List<UserEntity>>

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    fun getUserByEmailAndPassword(email: String, password: String): Single<UserEntity>

    @Update
    fun updateUser(user: UserEntity): Completable

    @Query("DELETE FROM users WHERE id = :userId")
    fun deleteUserById(userId: Int): Completable
}
