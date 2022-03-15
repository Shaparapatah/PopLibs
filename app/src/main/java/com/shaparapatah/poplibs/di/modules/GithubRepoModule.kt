package com.shaparapatah.poplibs.di.modules

import com.shaparapatah.poplibs.App
import com.shaparapatah.poplibs.di.scope.RepositoryScope
import com.shaparapatah.poplibs.di.scope.containers.ReposScopeContainer
import com.shaparapatah.poplibs.domain.GitHubRepoRepository
import com.shaparapatah.poplibs.domain.GithubRepoRepositoryImpl
import com.shaparapatah.poplibs.room.AppDataBase
import com.shaparapatah.poplibs.room.GithubRepoCache
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class GithubRepoModule {

    @RepositoryScope
    @Binds
    abstract fun bindReposRepo(impl: GithubRepoRepositoryImpl): GitHubRepoRepository


    companion object {

        @RepositoryScope
        @Provides
        fun repoCache(db: AppDataBase): GithubRepoCache {
            return GithubRepoCache(db)
        }

        @RepositoryScope
        @Provides
        fun scopeContainer(app: App): ReposScopeContainer = app

    }
}