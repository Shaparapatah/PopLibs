package com.shaparapatah.poplibs.model

import androidx.annotation.IntRange

class CountersModel {

    private val counters = Counters(0, 0, 0)


    fun increment0(): Int {
        counters.counter0 += 1
        return counters.counter0
    }

    fun increment1(): Int {
        counters.counter1 += 1
        return counters.counter1
    }

    fun increment2(): Int {
        counters.counter2 += 1
        return counters.counter2
    }

    fun set(@IntRange(from = 0, to = 2) index: Int, value: Int) {
        when (index) {
            0 -> counters.counter0 = value
            1 -> counters.counter1 = value
            2 -> counters.counter2 = value
            else -> error("Wrong index! ! !")
        }
    }
}