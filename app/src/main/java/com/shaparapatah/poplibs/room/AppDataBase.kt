package com.shaparapatah.poplibs.room

import androidx.room.Room
import androidx.room.RoomDatabase
import com.shaparapatah.poplibs.App
import com.shaparapatah.poplibs.room.dao.RepositoryDao
import com.shaparapatah.poplibs.room.dao.UserDao
import com.shaparapatah.poplibs.room.model.RoomGithubRepository
import com.shaparapatah.poplibs.room.model.RoomGithubUser


@androidx.room.Database(
    entities = [
        RoomGithubUser::class,
        RoomGithubRepository::class],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {

    abstract val userDao: UserDao
    abstract val repoDao: RepositoryDao

    companion object {
        private const val DB_NAME = "database.db"

        val instance by lazy {
            Room.databaseBuilder(App.instance, AppDataBase::class.java, DB_NAME)
                .build()
        }

    }
}