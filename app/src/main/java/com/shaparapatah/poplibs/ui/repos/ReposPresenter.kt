package com.shaparapatah.poplibs.ui.repos

import com.github.terrakok.cicerone.Router
import com.shaparapatah.poplibs.domain.GitHubRepoRepository
import com.shaparapatah.poplibs.model.GithubRepoModel
import com.shaparapatah.poplibs.model.GithubUserModel
import com.shaparapatah.poplibs.screens.AppScreens
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers.io
import moxy.MvpPresenter
import javax.inject.Inject

class ReposPresenter(
    private val userModel: GithubUserModel
) : MvpPresenter<ReposView>() {


    @Inject
    lateinit var router: Router

    @Inject
    lateinit var repo: GitHubRepoRepository

    @Inject
    lateinit var appScreens: AppScreens

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
        router.navigateTo(appScreens.onClickedReposScreen(repo))
    }


    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}