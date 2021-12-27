package com.shaparapatah.poplibs.domain

import com.shaparapatah.poplibs.model.GithubRepoModel
import com.shaparapatah.poplibs.model.GithubUserModel
import com.shaparapatah.poplibs.remote.RetrofitService
import com.shaparapatah.poplibs.remote.connectivity.NetworkStatus
import com.shaparapatah.poplibs.room.GithubRepoCache
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GithubRepoRepositoryImpl @Inject constructor(
    private val retrofitService: RetrofitService,
    private val networkStatus: NetworkStatus,
    private val repoCache: GithubRepoCache
) : GitHubRepoRepository {
    override fun getRepos(userModel: GithubUserModel): Single<List<GithubRepoModel>> {
        return if (networkStatus.isOnline()) {
            retrofitService.getRepos(userModel.reposUrl)
                .flatMap(repoCache::insertRepos)
        } else {
            repoCache.getRepos(userModel)
        }
    }

    override fun onClickedRepos(repoModel: GithubRepoModel): Single<List<GithubRepoModel>> {
        return if (networkStatus.isOnline()) {
            retrofitService.getRepos(repoModel.name)
                .flatMap(repoCache::insertRepos)
        } else {
            repoCache.getCliсkedRepos(repoModel)
        }
    }
}
