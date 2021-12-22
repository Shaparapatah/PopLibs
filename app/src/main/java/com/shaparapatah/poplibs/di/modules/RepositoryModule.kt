package com.shaparapatah.poplibs.di.modules

import com.shaparapatah.poplibs.domain.GitHubRepoRepository
import com.shaparapatah.poplibs.domain.GitHubUsersRepository
import com.shaparapatah.poplibs.domain.GithubRepoRepositoryImpl
import com.shaparapatah.poplibs.domain.GithubUsersRepositoryImpl
import com.shaparapatah.poplibs.remote.RetrofitService
import com.shaparapatah.poplibs.remote.connectivity.NetworkStatus
import com.shaparapatah.poplibs.room.GithubRepoCache
import com.shaparapatah.poplibs.room.GithubUserCache
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun usersRepo(
        networkStatus: NetworkStatus,
        retrofitService: RetrofitService,
        usersCache: GithubUserCache
    ): GitHubUsersRepository {
        return GithubUsersRepositoryImpl(retrofitService, networkStatus, usersCache)
    }

    @Provides
    fun reposRepo(
        retrofitService: RetrofitService,
        networkStatus: NetworkStatus,
        repoCache: GithubRepoCache
    ): GitHubRepoRepository {
        return GithubRepoRepositoryImpl(retrofitService, networkStatus, repoCache)
    }
}