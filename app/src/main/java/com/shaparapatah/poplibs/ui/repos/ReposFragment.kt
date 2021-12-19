package com.shaparapatah.poplibs.ui.repos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.shaparapatah.poplibs.App
import com.shaparapatah.poplibs.databinding.FragmentReposBinding
import com.shaparapatah.poplibs.domain.GithubRepoRepositoryImpl
import com.shaparapatah.poplibs.model.GithubRepoModel
import com.shaparapatah.poplibs.model.GithubUserModel
import com.shaparapatah.poplibs.remote.ApiHolder
import com.shaparapatah.poplibs.remote.connectivity.NetworkStatus
import com.shaparapatah.poplibs.room.AppDataBase
import com.shaparapatah.poplibs.room.GithubRepoCache
import com.shaparapatah.poplibs.ui.base.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class ReposFragment : MvpAppCompatFragment(), ReposView, BackButtonListener {


    private val presenter by moxyPresenter {
        ReposPresenter(
            router = App.instance.router,
            userModel = userModel!!,
            repo = GithubRepoRepositoryImpl(
                networkStatus = NetworkStatus(requireContext()),
                retrofitService = ApiHolder.retrofitService,
                repoCache = GithubRepoCache(AppDataBase.instance),
            )
        )
    }

    private val userModel: GithubUserModel by lazy {
        requireArguments().getSerializable(KEY_USER_MODEL) as GithubUserModel
    }

    private val adapter by lazy {
        ReposAdapter { presenter.onRepoClicked(it) }
    }


    private var _binding: FragmentReposBinding? = null
    private val binding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReposBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.reposRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.reposRecycler.adapter = adapter
    }

    override fun showLoading() {
        binding.loadingView.isVisible = true
        binding.reposRecycler.isVisible = false
    }

    override fun hideLoading() {
        binding.loadingView.isVisible = false
        binding.reposRecycler.isVisible = true
    }

    override fun showRepos(repos: List<GithubRepoModel>) {
        adapter.submitList(repos)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }

    companion object {

        private const val KEY_USER_MODEL = "KEY_USER_MODEL"

        fun newInstance(userModel: GithubUserModel): ReposFragment {
            return ReposFragment().apply {
                arguments = bundleOf(KEY_USER_MODEL to userModel)
            }
        }
    }

}
