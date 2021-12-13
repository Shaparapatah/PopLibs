package com.shaparapatah.poplibs.ui.users

import com.github.terrakok.cicerone.Router
import com.shaparapatah.poplibs.domain.GitHubUsersRepository
import com.shaparapatah.poplibs.model.GithubUser
import com.shaparapatah.poplibs.screens.AppScreens
import com.shaparapatah.poplibs.ui.base.IUserListPresenter
import com.shaparapatah.poplibs.ui.base.UserItemView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class UsersPresenter(
    private val router: Router,
    private val usersRepository: GitHubUsersRepository,
) : MvpPresenter<UsersView>() {

    class UserListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null


        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login.let {
                view.setLogin(user.login)
                view.setImageAvatar(user.avatarUrl)
            }
        }

        override fun getCount() = users.size

    }

    val userListPresenter = UserListPresenter()
    val disposables = CompositeDisposable()


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        viewState.showLoading()
        usersRepository.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<GithubUser>> {
                override fun onSubscribe(d: Disposable) {
                    disposables.add(d)
                }

                override fun onSuccess(t: List<GithubUser>?) {
                    if (t != null) {
                        viewState.hideLoading()
                        userListPresenter.users.addAll(t)
                        userListPresenter.itemClickListener = { itemView ->
                            router.navigateTo(AppScreens.userInfo(t[itemView.pos].login))
                        }
                        viewState.updateList()
                    }
                }

                override fun onError(e: Throwable) {
                    Throwable("Ошибка")
                    viewState.hideLoading()
                }

            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}