package com.shaparapatah.poplibs.screens

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.shaparapatah.poplibs.ui.userinfo.UserInfoFragment

import com.shaparapatah.poplibs.ui.users.UsersFragment

object AppScreens : IScreens {

    override fun users() = FragmentScreen {
        UsersFragment()
    }

    override fun userInfo(userLogin: String): Screen = FragmentScreen("user($userLogin)") {
        UserInfoFragment.getNewInstance(userLogin)

    }
}
