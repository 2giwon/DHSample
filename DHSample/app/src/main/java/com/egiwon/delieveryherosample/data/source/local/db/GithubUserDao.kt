package com.egiwon.delieveryherosample.data.source.local.db

import androidx.room.*
import com.egiwon.delieveryherosample.data.User
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface GithubUserDao {

    @Query("SELECT * FROM users")
    fun getLikeUsers(): Flowable<List<User>>

    @Query("SELECT * FROM users WHERE name LIKE '%' || :query  || '%'")
    fun searchLikeUsers(query: String): Flowable<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setLikeUser(user: User): Completable

    @Delete
    fun removeLikeUser(user: User): Completable
}