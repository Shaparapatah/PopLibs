package com.shaparapatah.poplibs.domain

import com.shaparapatah.poplibs.model.GithubUserModel
import com.shaparapatah.poplibs.remote.RetrofitService
import io.reactivex.rxjava3.core.Single

class GithubUsersRepositoryImpl(
    private val retrofitService: RetrofitService,
) : GitHubUsersRepository {

    override fun getUsers(): Single<List<GithubUserModel>> {
        return retrofitService.getUsers()
    }
}