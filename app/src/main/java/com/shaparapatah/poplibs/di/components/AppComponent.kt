package com.shaparapatah.poplibs.di.components

import com.shaparapatah.poplibs.di.modules.AppModule
import com.shaparapatah.poplibs.di.modules.CiceroneModule
import com.shaparapatah.poplibs.di.modules.DbModule
import com.shaparapatah.poplibs.di.modules.NetworkModule
import com.shaparapatah.poplibs.ui.main.MainActivity
import com.shaparapatah.poplibs.ui.main.MainPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DbModule::class,
        CiceroneModule::class,
        AppModule::class,
        NetworkModule::class,
    ]
)
interface AppComponent {

    fun userSubcomponent(): GithubUsersSubcomponent

    fun mainPresenter(): MainPresenter

    fun inject(mainActivity: MainActivity)
}