package com.shaparapatah.poplibs.di.modules

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.shaparapatah.poplibs.screens.AppScreens
import com.shaparapatah.poplibs.screens.AppScreensImpl
import dagger.Module
import dagger.Provides

@Module
class CiceroneModule {

    private val cicerone: Cicerone<Router> by lazy { Cicerone.create() }

    @Provides
    fun navigatorHolder(): NavigatorHolder {
        return cicerone.getNavigatorHolder()
    }

    @Provides
    fun router(): Router {
        return cicerone.router
    }

    @Provides

    fun appScreens(): AppScreens {
        return AppScreensImpl()
    }

}