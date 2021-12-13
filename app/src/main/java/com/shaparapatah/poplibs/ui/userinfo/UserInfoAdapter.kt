package com.shaparapatah.poplibs.ui.userinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shaparapatah.poplibs.databinding.RepositoryListItemBinding
import com.shaparapatah.poplibs.ui.base.IReposListPresenter
import com.shaparapatah.poplibs.ui.base.RepoItemView

class UserInfoAdapter(
    val presenter: IReposListPresenter
) : RecyclerView.Adapter<UserInfoAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        RepositoryListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply {
            pos = position
        })


    inner class ViewHolder(val vb: RepositoryListItemBinding) : RecyclerView.ViewHolder(vb.root),
        RepoItemView {
        override fun setName(text: String) {
            vb.tvName.text = text
        }

        override fun setDescription(text: String) {
            vb.tvDescription.text = text
        }

        override var pos = -1

    }

    override fun getItemCount() = presenter.getCount()

}
