package com.shaparapatah.poplibs.ui.repos

import com.github.terrakok.cicerone.Router
import com.shaparapatah.poplibs.di.scope.containers.ReposScopeContainer
import com.shaparapatah.poplibs.domain.GitHubRepoRepository
import com.shaparapatah.poplibs.model.GithubRepoModel
import com.shaparapatah.poplibs.model.GithubUserModel
import com.shaparapatah.poplibs.screens.AppScreens
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers.io
import moxy.MvpPresenter

class ReposPresenter @AssistedInject constructor(
    private var router: Router,
    private var repo: GitHubRepoRepository,
    private var appScreens: AppScreens,
    private val reposScopeContainer: ReposScopeContainer,
    @Assisted private val userModel: GithubUserModel
) : MvpPresenter<ReposView>() {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData()
    }


    override fun onDestroy() {
        reposScopeContainer.destroyRepoSubcomponent()
        super.onDestroy()

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

@AssistedFactory
interface ReposPresenterFactory {
    fun presenter(userModel: GithubUserModel): ReposPresenter
}