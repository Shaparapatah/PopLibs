package com.shaparapatah.poplibs.ui

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface MainView : MvpView {

    @AddToEndSingle
    fun setButtonText0(text: String)

    @AddToEndSingle
    fun setButtonText1(text: String)

    @AddToEndSingle
    fun setButtonText2(text: String)
}