package com.shaparapatah.poplibs.ui.users

import com.github.terrakok.cicerone.Router
import com.shaparapatah.poplibs.domain.GitHubUsersRepository
import com.shaparapatah.poplibs.model.GithubUserModel
import com.shaparapatah.poplibs.screens.AppScreens
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers.io
import moxy.MvpPresenter
import javax.inject.Inject

class UsersPresenter @Inject constructor(
    private var router: Router,
    private var usersRepository: GitHubUsersRepository,
    private var appScreens: AppScreens,
) : MvpPresenter<UsersView>() {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData()
    }

    private fun loadData() {
        usersRepository.getUsers()
            .subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showLoading() }
            .subscribe(
                { users ->
                    viewState.updateList(users)
                    viewState.hideLoading()

                }, {
                    Throwable("Ошибка загрузки пользователя")
                    viewState.hideLoading()
                })
    }

    fun onUserClicked(userModel: GithubUserModel) {
        router.navigateTo(appScreens.reposScreen(userModel))
    }


    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
