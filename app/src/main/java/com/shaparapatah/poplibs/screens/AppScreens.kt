package com.shaparapatah.poplibs.screens

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.shaparapatah.poplibs.model.GithubRepoModel
import com.shaparapatah.poplibs.model.GithubUserModel
import com.shaparapatah.poplibs.ui.clickrepo.ClickRepoFragment
import com.shaparapatah.poplibs.ui.repos.ReposFragment
import com.shaparapatah.poplibs.ui.users.UsersFragment

interface AppScreens {
    fun usersScreen(): FragmentScreen

    fun reposScreen(userModel: GithubUserModel): FragmentScreen

    fun onClickedReposScreen(repo: GithubRepoModel): FragmentScreen

}

class AppScreensImpl : AppScreens {

    override fun usersScreen() = FragmentScreen {
        UsersFragment()
    }

    override fun reposScreen(userModel: GithubUserModel) = FragmentScreen {
        ReposFragment.newInstance(userModel)

    }

    override fun onClickedReposScreen(repo: GithubRepoModel) = FragmentScreen {
        ClickRepoFragment.newInstance(repo)
    }
}
