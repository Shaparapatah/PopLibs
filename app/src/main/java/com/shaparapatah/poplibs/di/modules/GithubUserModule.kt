package com.shaparapatah.poplibs.di.modules

import com.shaparapatah.poplibs.App
import com.shaparapatah.poplibs.di.scope.UsersScope
import com.shaparapatah.poplibs.di.scope.containers.UsersScopeContainer
import com.shaparapatah.poplibs.domain.GitHubUsersRepository
import com.shaparapatah.poplibs.domain.GithubUsersRepositoryImpl
import com.shaparapatah.poplibs.room.AppDataBase
import com.shaparapatah.poplibs.room.GithubUserCache
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class GithubUserModule {


    @UsersScope
    @Binds
    abstract fun usersRepository(impl: GithubUsersRepositoryImpl): GitHubUsersRepository


    companion object {

        @Provides
        @UsersScope
        fun usersCache(db: AppDataBase): GithubUserCache {
            return GithubUserCache(db)
        }

        @UsersScope
        @Provides
        fun scopeContainer(app: App): UsersScopeContainer = app
    }
}