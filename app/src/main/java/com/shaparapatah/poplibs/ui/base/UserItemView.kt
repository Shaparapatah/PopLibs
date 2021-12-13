package com.shaparapatah.poplibs.ui.base

/**
 * Интерфейс элементов списка Users
 */

interface UserItemView : IItemView {

    fun setLogin(text: String)


    fun setImageAvatar(url: String)
}