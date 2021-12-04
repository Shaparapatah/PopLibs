package com.shaparapatah.poplibs.ui.users

import com.github.terrakok.cicerone.Router
import com.shaparapatah.poplibs.domain.GitHubUsersRepository
import com.shaparapatah.poplibs.model.GithubUserModel
import com.shaparapatah.poplibs.screens.AppScreens
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
        val users = usersRepository.getUsers()

        viewState.updateList(users)
    }

    fun onUserClicked(userModel: GithubUserModel) {
        router.navigateTo(AppScreens.userScreen(userModel.toString()))
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}