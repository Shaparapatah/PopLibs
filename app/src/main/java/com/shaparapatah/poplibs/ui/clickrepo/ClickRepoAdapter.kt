package com.shaparapatah.poplibs.ui.clickrepo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shaparapatah.poplibs.databinding.ItemRepoBinding
import com.shaparapatah.poplibs.model.GithubRepoModel

class ClickRepoAdapter(
    private val itemClickListener: (GithubRepoModel) -> Unit,
) : ListAdapter<GithubRepoModel, ClickRepoAdapter.RepoViewHolder>(GithubRepoItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(
            ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ClickRepoAdapter.RepoViewHolder, position: Int) {
        holder.showClickedRepo(currentList[position])
    }

    inner class RepoViewHolder(private val vb: ItemRepoBinding) : RecyclerView.ViewHolder(vb.root) {

        fun showClickedRepo(repo: GithubRepoModel) {
            vb.root.setOnClickListener { itemClickListener(repo) }

            vb.tvRepoName.text = repo.name
        }
    }


}

object GithubRepoItemCallback : DiffUtil.ItemCallback<GithubRepoModel>() {

    override fun areItemsTheSame(oldItem: GithubRepoModel, newItem: GithubRepoModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: GithubRepoModel, newItem: GithubRepoModel): Boolean {
        return oldItem == newItem
    }
}