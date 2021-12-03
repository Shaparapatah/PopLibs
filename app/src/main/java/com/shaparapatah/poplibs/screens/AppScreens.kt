package com.shaparapatah.poplibs.screens

import androidx.core.os.bundleOf
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.shaparapatah.poplibs.ui.users.FragmentUserView
import com.shaparapatah.poplibs.ui.users.UsersFragment

object AppScreens {

    fun usersScreen() = FragmentScreen {
        UsersFragment()
    }

    fun userScreen(userLogin: String) = FragmentScreen("user($userLogin)") {
        FragmentUserView.getNewInstance(userLogin)

    }
}
