package com.shaparapatah.poplibs.di.components

import com.shaparapatah.poplibs.di.modules.GithubUserModule
import com.shaparapatah.poplibs.di.scope.UsersScope
import com.shaparapatah.poplibs.ui.users.UsersPresenter
import dagger.Subcomponent

@UsersScope
@Subcomponent(
    modules = [
        GithubUserModule::class,
    ]
)
interface GithubUsersSubcomponent {

    fun repoSubcomponent(): GithubRepoSubcomponent

    fun provideUsersPresenter(): UsersPresenter
}