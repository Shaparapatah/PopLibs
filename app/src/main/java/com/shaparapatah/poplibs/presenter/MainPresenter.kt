package com.shaparapatah.poplibs.presenter

import com.shaparapatah.poplibs.model.CountersModel
import com.shaparapatah.poplibs.ui.MainView

class MainPresenter(
    private val view: MainView
) {

    private val model = CountersModel()


    fun onClicked0() {
        val nextValue = model.increment0()
        view.setButtonText0(nextValue.toString())
    }

    fun onClicked1() {
        val nextValue = model.increment1()
        view.setButtonText1(nextValue.toString())
    }

    fun onClicked2() {
        val nextValue = model.increment2()
        view.setButtonText2(nextValue.toString())
    }
}