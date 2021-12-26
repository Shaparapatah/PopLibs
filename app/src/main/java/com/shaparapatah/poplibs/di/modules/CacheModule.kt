package com.shaparapatah.poplibs.di.modules

import android.content.Context
import androidx.room.Room
import com.shaparapatah.poplibs.room.AppDataBase
import com.shaparapatah.poplibs.room.GithubRepoCache
import com.shaparapatah.poplibs.room.GithubUserCache
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

    @Singleton
    @Provides
    fun usersCache(
        db: AppDataBase
    ): GithubUserCache {
        return GithubUserCache(db)
    }

    @Singleton
    @Provides
    fun repoCache(
        db: AppDataBase
    ): GithubRepoCache {
        return GithubRepoCache(db)
    }
}