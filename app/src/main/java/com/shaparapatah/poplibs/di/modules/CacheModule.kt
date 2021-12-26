package com.shaparapatah.poplibs.di.modules

import android.content.Context
import androidx.room.Room
import com.shaparapatah.poplibs.room.AppDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

private const val DB_NAME = "database.db"

@Module
class CacheModule {

    @Singleton
    @Provides
    fun db(context: Context): AppDataBase = Room
        .databaseBuilder(context, AppDataBase::class.java, DB_NAME)
        .build()

//    @Provides
//    fun usersCache(context: Context) : AppDataBase = Room
//        .databaseBuilder(context, AppDataBase::class.java, DB_NAME)
//        .build()
//
//        @Provides
//    fun repoCache(context: Context) : AppDataBase = Room
//        .databaseBuilder(context, AppDataBase::class.java, DB_NAME)
//        .build()
}