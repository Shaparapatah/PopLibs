package com.shaparapatah.poplibs.domain

import com.shaparapatah.poplibs.model.GithubUser
import com.shaparapatah.poplibs.model.GithubUserModel
import com.shaparapatah.poplibs.model.Repository
import com.shaparapatah.poplibs.remote.RetrofitService
import io.reactivex.rxjava3.core.Single

class GithubUsersRepositoryImpl(
    private val retrofitService: RetrofitService,
) : GitHubUsersRepository {


    override fun getUsers(): Single<List<GithubUser>> {
        return retrofitService.getUsers()
    }

    override fun getUserByLogin(login: String): Single<GithubUserModel> {
        return retrofitService.getUserByLogin(login)
    }

    override fun getUserRepos(
        login: String,
        type: String?,
        sort: String?,
        direction: String?,
        perPage: Int?,
        page: Int?
    ): Single<List<Repository>> {
        return retrofitService.getUserRepos(login, type, sort, direction, perPage, page)
    }
}