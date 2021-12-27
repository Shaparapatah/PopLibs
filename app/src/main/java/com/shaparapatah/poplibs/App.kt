package com.shaparapatah.poplibs

import android.app.Application
import com.shaparapatah.poplibs.di.components.AppComponent
import com.shaparapatah.poplibs.di.components.DaggerAppComponent
import com.shaparapatah.poplibs.di.components.GithubRepoSubcomponent
import com.shaparapatah.poplibs.di.components.GithubUsersSubcomponent
import com.shaparapatah.poplibs.di.modules.AppModule
import com.shaparapatah.poplibs.di.scope.containers.ReposScopeContainer
import com.shaparapatah.poplibs.di.scope.containers.UsersScopeContainer

class App : Application(), UsersScopeContainer, ReposScopeContainer {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    var userSubcomponent: GithubUsersSubcomponent? = null

    var repoSubcomponent: GithubRepoSubcomponent? = null

    override fun onCreate() {
        super.onCreate()
        _instance = this
    }


    override fun initUsersSubcomponent() = appComponent.userSubcomponent().also {
        userSubcomponent = it
    }

    override fun destroyUsersSubcomponent() {
        userSubcomponent = null
    }

    override fun initRepoSubcomponent() = appComponent.userSubcomponent().repoSubcomponent().also {
        repoSubcomponent = it
    }

    override fun destroyRepoSubcomponent() {
        repoSubcomponent = null
    }


    companion object {
        private var _instance: App? = null
        val instance
            get() = _instance!!
    }
}

