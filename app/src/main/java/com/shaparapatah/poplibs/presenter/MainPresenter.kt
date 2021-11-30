package com.shaparapatah.poplibs.presenter

import com.shaparapatah.poplibs.model.CountersModel
import com.shaparapatah.poplibs.ui.MainView
import moxy.MvpPresenter

class MainPresenter(
    private val model: CountersModel
) : MvpPresenter<MainView>() {


    fun onClicked0() {
        val nextValue = model.increment0()
        viewState.setButtonText0(nextValue.toString())
    }

    fun onClicked1() {
        val nextValue = model.increment1()
        viewState.setButtonText1(nextValue.toString())
    }

    fun onClicked2() {
        val nextValue = model.increment2()
        viewState.setButtonText2(nextValue.toString())
    }
}