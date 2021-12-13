package com.shaparapatah.poplibs.ui.imageloading

interface ImageLoader<T> {

    fun loadInto(url: String, container: T)
}