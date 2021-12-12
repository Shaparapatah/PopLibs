package com.shaparapatah.poplibs.ui.users

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.shaparapatah.poplibs.domain.GitHubUsersRepository
import com.shaparapatah.poplibs.model.GithubUserModel
import com.shaparapatah.poplibs.screens.AppScreens
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class UsersPresenter(
    private val router: Router,
    private val usersRepository: GitHubUsersRepository,
) : MvpPresenter<UsersView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData()
    }

    private fun loadData() {
        usersRepository.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showLoading() }
            .subscribe(
                { users ->
                    viewState.updateList(users)
                    viewState.hideLoading()
                }, { e ->
                    Log.e("Retrofit", "Ошибка при получении пользователей", e)
                    viewState.hideLoading()
                })
    }

    fun onUserClicked(userModel: GithubUserModel) {
        //todo   router.navigateTo(AppScreens.userScreen(userModel.toString()))
    }


    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}