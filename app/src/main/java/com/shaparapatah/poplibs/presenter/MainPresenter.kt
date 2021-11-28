package com.shaparapatah.poplibs.presenter

import com.shaparapatah.poplibs.model.CountersModel
import com.shaparapatah.poplibs.ui.MainView

class MainPresenter(
    private val view: MainView
) {

    private val model = CountersModel()


    fun btnClick() {
        view.let {
            val text = model.increment(0)
            it.setButtonText(text.toString())
        }
    }
}