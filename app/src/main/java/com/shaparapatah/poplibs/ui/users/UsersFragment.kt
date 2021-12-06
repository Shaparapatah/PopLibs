package com.shaparapatah.poplibs.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.shaparapatah.poplibs.App
import com.shaparapatah.poplibs.databinding.FragmentUserBinding
import com.shaparapatah.poplibs.domain.GitHubUsersRepository
import com.shaparapatah.poplibs.model.GithubUserModel
import com.shaparapatah.poplibs.ui.base.BackButtonListener
import com.shaparapatah.poplibs.ui.users.adapter.UsersAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private val presenter by moxyPresenter {
        UsersPresenter(
            App.instance.router,
            GitHubUsersRepository()
        )
    }

    private var _binding: FragmentUserBinding? = null
    private val binding
        get() = _binding!!

    private val adapter by lazy {
        UsersAdapter(presenter::onUserClicked)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.usersRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.usersRecycler.adapter = adapter

    }

    override fun updateList(users: List<GithubUserModel>) {
        adapter.submitList(users)
    }


    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
