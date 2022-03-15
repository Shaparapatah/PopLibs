package com.shaparapatah.poplibs.domain

import com.shaparapatah.poplibs.model.GithubUserModel
import com.shaparapatah.poplibs.remote.RetrofitService
import com.shaparapatah.poplibs.remote.connectivity.NetworkStatus
import com.shaparapatah.poplibs.room.GithubUserCache
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GithubUsersRepositoryImpl @Inject constructor(
    private val retrofitService: RetrofitService,
    private val networkStatus: NetworkStatus,
    private val usersCache: GithubUserCache
) : GitHubUsersRepository {
    override fun getUsers(): Single<List<GithubUserModel>> {
        return if (networkStatus.isOnline()) {
            retrofitService.getUsers()
                .flatMap(usersCache::insert)
        } else {
            usersCache.getUsers()
        }
    }


}