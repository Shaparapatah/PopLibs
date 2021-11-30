package com.shaparapatah.poplibs.ui.users

import com.shaparapatah.poplibs.domain.GitHubUsersRepository
import com.shaparapatah.poplibs.model.GithubUserModel
import com.shaparapatah.poplibs.ui.base.IListPresenter
import moxy.MvpPresenter

class UsersPresenter(
    private val usersRepository: GitHubUsersRepository
) : MvpPresenter<UsersView>() {

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()

        usersListPresenter.itemClickListener = {} //todo
    }

    private fun loadData() {
        val users = usersRepository.getUsers()
        usersListPresenter.users.addAll(users)
    }


    class UsersListPresenter : IListPresenter<UserItemView> {

        val users = mutableListOf<GithubUserModel>()

        override var itemClickListener: () -> Unit = {}

        override fun getCount() = users.size


        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }

    }
}