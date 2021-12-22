package com.shaparapatah.poplibs.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(val app: Application) {

    @Provides
    fun app(): Context {
        return app()
    }
}