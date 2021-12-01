package com.shaparapatah.poplibs.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.shaparapatah.poplibs.App
import com.shaparapatah.poplibs.databinding.ActivityMainBinding
import com.shaparapatah.poplibs.databinding.FragmentUserBinding
import com.shaparapatah.poplibs.domain.GitHubUsersRepository
import com.shaparapatah.poplibs.ui.base.BackButtonListener
import com.shaparapatah.poplibs.ui.users.adapter.UsersAdapter
import moxy.MvpAppCompatActivity
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
        UsersAdapter(presenter.usersListPresenter)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.usersRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.usersRecycler.adapter = adapter
    }


    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }
}
