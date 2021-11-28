package com.shaparapatah.poplibs.model

import androidx.annotation.IntRange

class CountersModel {

    private val counters = mutableListOf(0, 0, 0)

    private fun getCurrent(@IntRange(from = 0, to = 2) index: Int): Int {
        return counters[index]
    }

    fun increment(@IntRange(from = 0, to = 2) index: Int): Int {
        counters[index]++
        return getCurrent(index)
    }

    fun set(index: Int, value: Int) {
        counters[index] = value
    }
}