package com.shaparapatah.poplibs.ui.main

import com.github.terrakok.cicerone.Router
import com.shaparapatah.poplibs.screens.AppScreens
import moxy.MvpPresenter

class MainPresenter(
    private val router: Router,
) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        router.replaceScreen(AppScreens.usersScreen())

        router.navigateTo(AppScreens.userScreen(viewState.toString()))
    }

    fun backPressed() {
        router.exit()
    }
}