package com.shaparapatah.poplibs.di.components

import com.shaparapatah.poplibs.di.modules.*
import com.shaparapatah.poplibs.ui.main.MainActivity
import com.shaparapatah.poplibs.ui.main.MainPresenter
import com.shaparapatah.poplibs.ui.repos.ReposPresenter
import com.shaparapatah.poplibs.ui.users.UsersPresenter
import dagger.Component

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

    fun inject(mainActivity: MainActivity)

    fun inject(mainPresenter: MainPresenter)

    fun inject(usersPresenter: UsersPresenter)

    fun inject(reposPresenter: ReposPresenter)
}