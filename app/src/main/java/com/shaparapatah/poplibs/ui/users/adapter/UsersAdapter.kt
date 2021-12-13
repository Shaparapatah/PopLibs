package com.shaparapatah.poplibs.ui.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shaparapatah.poplibs.databinding.ItemUserBinding
import com.shaparapatah.poplibs.databinding.ListItemBinding
import com.shaparapatah.poplibs.model.GithubUserModel
import com.shaparapatah.poplibs.ui.base.IUserListPresenter
import com.shaparapatah.poplibs.ui.base.UserItemView
import com.shaparapatah.poplibs.ui.imageloading.ImageLoader

class UsersAdapter(
    private val presenter: IUserListPresenter,
    private val imageLoader: ImageLoader<ImageView>
) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    ).apply {
        itemView.setOnClickListener {
            presenter.itemClickListener
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    override fun getItemCount() = presenter.getCount()


    inner class ViewHolder(private val vb: ListItemBinding) : RecyclerView.ViewHolder(vb.root),
        UserItemView {
        override fun setLogin(text: String) {
            vb.tvLogin.text = text
        }

        override fun setImageAvatar(url: String) {
            imageLoader.loadInto(url, vb.imageViewUserAvatar)
        }

        override var pos = -1

    }
}






