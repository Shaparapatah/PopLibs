package com.shaparapatah.poplibs.di.components

import com.shaparapatah.poplibs.di.modules.*
import com.shaparapatah.poplibs.ui.main.MainActivity
import com.shaparapatah.poplibs.ui.main.MainPresenter
import com.shaparapatah.poplibs.ui.repos.ReposPresenter
import com.shaparapatah.poplibs.ui.users.UsersPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CacheModule::class,
        CiceroneModule::class,
        ContextModule::class,
        NetworkModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {

    fun mainPresenter(): MainPresenter

    fun usersPresenter(): UsersPresenter

    fun reposPresenterFactory(): ReposPresenter.ReposPresenterFactory

    fun inject(mainActivity: MainActivity)
}