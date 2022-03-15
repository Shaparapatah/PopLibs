package com.shaparapatah.poplibs.di.components

import com.shaparapatah.poplibs.di.modules.GithubRepoModule
import com.shaparapatah.poplibs.di.scope.RepositoryScope
import com.shaparapatah.poplibs.ui.repos.ReposPresenterFactory
import dagger.Subcomponent

@RepositoryScope
@Subcomponent(
    modules = [
        GithubRepoModule::class
    ]
)
interface GithubRepoSubcomponent {

    fun reposPresenterFactory(): ReposPresenterFactory
}