package com.egiwon.delieveryherosample.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.egiwon.delieveryherosample.data.source.local.model.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GithubDataBase : RoomDatabase() {
    abstract fun githubUserDao(): GithubUserDao

    companion object {
        const val DB_NAME = "Users.db"
    }
}