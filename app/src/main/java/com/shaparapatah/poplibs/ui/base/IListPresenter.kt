package com.shaparapatah.poplibs.ui.base

import com.shaparapatah.poplibs.ui.users.UserItemView

interface IListPresenter<V : IItemView> {

    var itemClickListener: (UserItemView) -> Unit

    fun getCount(): Int

    fun bindView(view: V)
}