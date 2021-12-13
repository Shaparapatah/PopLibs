package com.shaparapatah.poplibs.ui.userinfo

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface UserInfoView : MvpView {

    @AddToEndSingle
    fun showLogin(text: String)

    @AddToEndSingle
    fun setImageAvatar(url: String)

    @AddToEndSingle
    fun showTopString(text: String)

    @AddToEndSingle
    fun init()

    @AddToEndSingle
    fun updateList()
}