package com.shaparapatah.poplibs.ui.users

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.shaparapatah.poplibs.databinding.ActivityMainBinding
import com.shaparapatah.poplibs.databinding.FragmentUserBinding
import com.shaparapatah.poplibs.domain.GitHubUsersRepository
import com.shaparapatah.poplibs.ui.users.adapter.UsersAdapter
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class UsersActivity : MvpAppCompatActivity(), UsersView {

    private val presenter by moxyPresenter { UsersPresenter(GitHubUsersRepository()) }

    private var _binding: FragmentUserBinding? = null
    private val binding
        get() = _binding!!

    private val adapter by lazy {
        UsersAdapter(presenter.usersListPresenter)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = FragmentUserBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.usersRecycler.layoutManager = LinearLayoutManager(this)
        binding.usersRecycler.adapter = adapter
    }


    override fun updateList() {
        adapter.notifyDataSetChanged()
    }
}
