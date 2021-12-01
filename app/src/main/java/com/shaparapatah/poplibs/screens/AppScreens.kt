package com.shaparapatah.poplibs.screens

import androidx.core.os.bundleOf
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.shaparapatah.poplibs.ui.users.UsersFragment
import com.shaparapatah.poplibs.ui.users.adapter.FragmentUserView

object AppScreens {

    fun usersScreen() = FragmentScreen {
        UsersFragment()
    }

    fun userScreen(userLogin: String) = FragmentScreen {
        FragmentUserView().apply {
            arguments = bundleOf(
                "userLogin" to userLogin
            )
        }
    }
}