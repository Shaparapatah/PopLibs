package com.shaparapatah.poplibs.ui.userinfo

import com.github.terrakok.cicerone.Router
import com.shaparapatah.poplibs.domain.GithubUsersRepositoryImpl
import com.shaparapatah.poplibs.model.GithubUserModel
import com.shaparapatah.poplibs.model.Repository
import com.shaparapatah.poplibs.screens.AppScreens
import com.shaparapatah.poplibs.ui.base.IReposListPresenter
import com.shaparapatah.poplibs.ui.base.RepoItemView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class UserInfoPresenter(
    val userLogin: String? = null,
    private val githubUsersRepo: GithubUsersRepositoryImpl,
    val router: Router
) : MvpPresenter<UserInfoView>() {
    val disposables = CompositeDisposable()

    class ReposListPresenter : IReposListPresenter {
        val repositories = mutableListOf<Repository>()
        override var itemClickListener: ((RepoItemView) -> Unit)? = null
        override fun getCount() = repositories.size

        override fun bindView(view: RepoItemView) {
            val repos = repositories[view.pos]
            repos.name.let {
                view.setName(repos.name)
                repos.description?.let { view.setDescription((repos.description)) }
            }
        }
    }

    val reposListPresenter = ReposListPresenter()

    override fun onFirstViewAttach() {
        if (userLogin != null) {
            viewState.init()
            githubUsersRepo
                .getUserByLogin(userLogin)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<GithubUserModel> {
                    override fun onSubscribe(d: Disposable?) {
                        disposables.add(d)
                    }

                    override fun onSuccess(iserInfo: GithubUserModel?) {
                        if (iserInfo != null) {
                            iserInfo.let {
                                viewState.showLogin(it.login)
                                viewState.setImageAvatar(it.avatarUrl)
                                viewState.showTopString("Верхняя строка")
                                githubUsersRepo
                                    .getUserRepos(userLogin, null, null, null, 99, 1)
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(object : SingleObserver<List<Repository>> {
                                        override fun onSubscribe(d: Disposable?) {
                                            disposables.add(d)
                                        }

                                        override fun onSuccess(t: List<Repository>) {
                                            viewState.showTopString("Загружено  репозиториев :" + t.size + " из " + iserInfo.publicRepos)
                                            reposListPresenter.repositories.addAll(t)
                                            reposListPresenter.itemClickListener = { itemView ->
                                                router.navigateTo(AppScreens.userInfo(t[itemView.pos].name))
                                            }
                                            viewState.updateList()
                                        }

                                        override fun onError(e: Throwable?) {
                                            viewState.showTopString("Ошибка при попытке чтения спискаа репозиториев")
                                        }
                                    })

                            }
                        }
                    }

                    override fun onError(e: Throwable?) {

                    }
                })
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        disposables.clear()
    }
}
