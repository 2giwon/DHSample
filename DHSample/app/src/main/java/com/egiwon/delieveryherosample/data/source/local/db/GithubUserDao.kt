package com.egiwon.delieveryherosample.data.source.local.db

import androidx.room.*
import com.egiwon.delieveryherosample.data.source.local.model.UserEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface GithubUserDao {

    @Query("SELECT * FROM users")
    fun getLikeUsers(): Single<List<UserEntity>>

    @Query("SELECT * FROM users WHERE name LIKE '%' || :query  || '%'")
    fun searchLikeUsers(query: String): Single<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setLikeUser(user: UserEntity): Completable

    @Delete
    fun removeLikeUser(user: UserEntity): Completable
}