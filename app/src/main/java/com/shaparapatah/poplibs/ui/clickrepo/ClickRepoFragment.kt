package com.shaparapatah.poplibs.ui.clickrepo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import com.shaparapatah.poplibs.App
import com.shaparapatah.poplibs.databinding.FragmentClickReposBinding
import com.shaparapatah.poplibs.domain.GithubRepoRepositoryImpl
import com.shaparapatah.poplibs.model.GithubRepoModel
import com.shaparapatah.poplibs.model.GithubUserModel
import com.shaparapatah.poplibs.remote.ApiHolder
import com.shaparapatah.poplibs.remote.connectivity.NetworkStatus
import com.shaparapatah.poplibs.room.AppDataBase
import com.shaparapatah.poplibs.ui.base.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class ClickRepoFragment : MvpAppCompatFragment(), ClickRepoView, BackButtonListener {

    private val presenter by moxyPresenter {
        ClickRepoPresenter(
            router = App.instance.router,
            repoModel = repoModel!!,
            repo = GithubRepoRepositoryImpl(
                networkStatus = NetworkStatus(requireContext()),
                retrofitService = ApiHolder.retrofitService,
                db = AppDataBase.instance
            )
        )
    }

    private var _binding: FragmentClickReposBinding? = null
    private val binding
        get() = _binding!!


//    private val adapter by lazy {
//        ClickRepoAdapter { presenter.onRepoClicked(it) }
//    }

    private val repoModel: GithubRepoModel by lazy {
        requireArguments().getSerializable(KEY_REPO_MODEL) as GithubRepoModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClickReposBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun showRepos(repos: List<GithubRepoModel>) {
        binding.textViewRepos.text = repos.toString()
    }



    override fun showLoading() {
        binding.loadingView.isVisible = true
        binding.textViewRepos.isVisible = false
    }

    override fun hideLoading() {
        binding.loadingView.isVisible = false
        binding.textViewRepos.isVisible = true
    }


    companion object {

        private const val KEY_REPO_MODEL = "KEY_REPO_MODEL"

        fun newInstance(repoModel: GithubRepoModel) = ClickRepoFragment().apply {
            arguments = bundleOf(KEY_REPO_MODEL to repoModel)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }
}