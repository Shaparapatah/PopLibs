package com.shaparapatah.poplibs.screens

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.shaparapatah.poplibs.model.GithubUserModel
import com.shaparapatah.poplibs.ui.repos.ReposFragment
import com.shaparapatah.poplibs.ui.users.UsersFragment

object AppScreens {

    fun usersScreen(): Screen = FragmentScreen {
        UsersFragment()
    }

    fun reposScreen(userModel: GithubUserModel): Screen = FragmentScreen {
        ReposFragment.newInstance(userModel)

    }


}
