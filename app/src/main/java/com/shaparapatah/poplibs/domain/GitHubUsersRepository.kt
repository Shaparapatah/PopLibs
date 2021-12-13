package com.shaparapatah.poplibs.domain

import com.shaparapatah.poplibs.model.GithubUser
import com.shaparapatah.poplibs.model.GithubUserModel
import com.shaparapatah.poplibs.model.Repository
import io.reactivex.rxjava3.core.Single

interface GitHubUsersRepository {

    fun getUsers(): Single<List<GithubUser>>

    fun getUserByLogin(login: String): Single<GithubUserModel>

    fun getUserRepos(
        login: String,
        type: String?,
        sort: String?,
        direction: String?,
        perPage: Int?,
        page: Int?
    ): Single<List<Repository>>

}