package com.shaparapatah.poplibs.ui.clickrepo

import com.github.terrakok.cicerone.Router
import com.shaparapatah.poplibs.domain.GitHubRepoRepository
import com.shaparapatah.poplibs.model.GithubRepoModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers.io
import moxy.MvpPresenter

class ClickRepoPresenter(
    private val repoModel: GithubRepoModel,
    private val router: Router,
    private val repo: GitHubRepoRepository
) : MvpPresenter<ClickRepoView>() {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData()
    }

    private fun loadData() {
        repo.onClickedRepos(repoModel)
            .subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showLoading() }
            .subscribe(
                { repos ->
                    viewState.showRepos(repos)
                    viewState.hideLoading()

            },{
                Throwable("Ошибка загрузки репозитория")
                    viewState.hideLoading()
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}