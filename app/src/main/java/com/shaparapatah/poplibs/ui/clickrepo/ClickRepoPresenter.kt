package com.shaparapatah.poplibs.ui.clickrepo

import com.shaparapatah.poplibs.model.GithubRepoModel
import moxy.MvpPresenter

class ClickRepoPresenter(
    private val repoModel: GithubRepoModel,

) : MvpPresenter<ClickRepoView>() {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()


        viewState.showRepos(repoModel)
    }

}