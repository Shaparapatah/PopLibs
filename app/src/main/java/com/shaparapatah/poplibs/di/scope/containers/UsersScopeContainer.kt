package com.shaparapatah.poplibs.di.scope.containers

import com.shaparapatah.poplibs.di.components.GithubUsersSubcomponent

interface UsersScopeContainer {

    fun initUsersSubcomponent(): GithubUsersSubcomponent

    fun destroyUsersSubcomponent()
}