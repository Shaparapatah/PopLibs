package com.shaparapatah.poplibs.domain

import com.shaparapatah.poplibs.model.GithubRepoModel
import com.shaparapatah.poplibs.model.GithubUserModel
import io.reactivex.rxjava3.core.Single

interface GitHubRepoRepository {

    fun getRepos(userModel: GithubUserModel) : Single<List<GithubRepoModel>>

    fun onClickedRepos(repoModel : GithubRepoModel) : Single<List<GithubRepoModel>>

}