package com.shaparapatah.poplibs.ui.main

import com.github.terrakok.cicerone.Router
import com.shaparapatah.poplibs.screens.AppScreens
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val router: Router,
    private val appScreens: AppScreens
) : MvpPresenter<MainView>() {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        router.replaceScreen(appScreens.usersScreen())

    }

    fun backPressed() {
        router.exit()
    }
}