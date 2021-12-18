package com.shaparapatah.poplibs.ui.repos

import com.shaparapatah.poplibs.model.GithubRepoModel
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface ReposView : MvpView {

    fun showLoading()

    fun hideLoading()

    fun showRepos(repos: List<GithubRepoModel>)
}