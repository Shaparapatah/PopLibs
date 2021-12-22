package com.shaparapatah.poplibs.ui.clickrepo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.shaparapatah.poplibs.databinding.FragmentClickReposBinding
import com.shaparapatah.poplibs.model.GithubRepoModel
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class ClickRepoFragment : MvpAppCompatFragment(), ClickRepoView {

    private val presenter by moxyPresenter {
        ClickRepoPresenter(repoModel)
    }

    private var _binding: FragmentClickReposBinding? = null
    private val binding
        get() = _binding!!


    private val repoModel by lazy {
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


    override fun showRepos(repos: GithubRepoModel) {
        "Количество форков : ${repos.forksCount}".also { binding.textViewRepos.text = it }

        binding.textViewName.text = repos.name
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
}