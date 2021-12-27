package com.shaparapatah.poplibs.di.scope.containers

import com.shaparapatah.poplibs.di.components.GithubRepoSubcomponent

interface ReposScopeContainer {

    fun initRepoSubcomponent(): GithubRepoSubcomponent

    fun destroyRepoSubcomponent()
}