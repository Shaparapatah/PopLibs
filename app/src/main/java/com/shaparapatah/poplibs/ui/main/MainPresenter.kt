package com.shaparapatah.poplibs.ui.main

import com.github.terrakok.cicerone.Router
import com.shaparapatah.poplibs.screens.IScreens
import moxy.MvpPresenter

class MainPresenter(
    private val router: Router,
    val screens: IScreens
) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        router.replaceScreen(screens.users())

    }

    fun backPressed() {
        router.exit()
    }
}