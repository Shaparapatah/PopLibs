package com.shaparapatah.poplibs.ui.clickrepo

import com.shaparapatah.poplibs.model.GithubRepoModel
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface ClickRepoView : MvpView {

    fun showRepos(repos: List<GithubRepoModel>)

    fun showLoading()

    fun hideLoading()
}