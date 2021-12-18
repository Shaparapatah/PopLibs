package com.shaparapatah.poplibs.ui.repos

import com.github.terrakok.cicerone.Router
import com.shaparapatah.poplibs.domain.GitHubRepoRepository
import com.shaparapatah.poplibs.model.GithubRepoModel
import com.shaparapatah.poplibs.model.GithubUserModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers.io
import moxy.MvpPresenter

class ReposPresenter(
    private val userModel: GithubUserModel,
    private val router: Router,
    private val repo: GitHubRepoRepository
) : MvpPresenter<ReposView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData()
    }

    private fun loadData() {
        repo.getRepos(userModel)
            .subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showLoading() }
            .subscribe(
                { repos ->
                    viewState.showRepos(repos)
                    viewState.hideLoading()

                }, {
                    Throwable("Ошибка загрузки репозиториев")
                    viewState.hideLoading()
                })
    }

    fun onRepoClicked(repo: GithubRepoModel) {
        //todo
    }


    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}