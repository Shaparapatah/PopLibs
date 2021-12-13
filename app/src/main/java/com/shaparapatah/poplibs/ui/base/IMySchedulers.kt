package com.shaparapatah.poplibs.ui.base

import io.reactivex.rxjava3.core.Scheduler


interface IMySchedulers {

    fun main(): Scheduler


    fun computation(): Scheduler


}