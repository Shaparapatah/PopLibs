package com.shaparapatah.poplibs.ui.main

import android.net.Uri
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface ImageConverterView : MvpView {

    @AddToEndSingle
    fun init()

    @AddToEndSingle
    fun showOriginalImage(uri: Uri)

    @AddToEndSingle
    fun showConvertedImage(uri: Uri)
}