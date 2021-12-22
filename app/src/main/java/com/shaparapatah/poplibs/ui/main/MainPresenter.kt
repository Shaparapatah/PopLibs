package com.shaparapatah.poplibs.ui.main

import com.github.terrakok.cicerone.Router
import com.shaparapatah.poplibs.screens.AppScreens
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var appScreens: AppScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        router.replaceScreen(appScreens.usersScreen())

    }

    fun backPressed() {
        router.exit()
    }
}