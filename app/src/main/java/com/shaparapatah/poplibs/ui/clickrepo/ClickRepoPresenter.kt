package com.shaparapatah.poplibs.ui.clickrepo

import com.github.terrakok.cicerone.Router
import com.shaparapatah.poplibs.domain.GitHubRepoRepository
import com.shaparapatah.poplibs.model.GithubRepoModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers.io
import moxy.MvpPresenter

class ClickRepoPresenter(
    private val repoModel: GithubRepoModel,

) : MvpPresenter<ClickRepoView>() {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()


        viewState.showRepos(repoModel)
    }

}