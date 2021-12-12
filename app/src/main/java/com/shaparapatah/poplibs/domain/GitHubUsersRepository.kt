package com.shaparapatah.poplibs.domain

import com.shaparapatah.poplibs.model.GithubUserModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface GitHubUsersRepository {

    fun getUsers(): Single<List<GithubUserModel>>


}