package com.shaparapatah.poplibs.ui.base

interface IListPresenter<V : IItemView> {

    var itemClickListener: () -> Unit

    fun getCount(): Int

    fun bindView(view: V)
}