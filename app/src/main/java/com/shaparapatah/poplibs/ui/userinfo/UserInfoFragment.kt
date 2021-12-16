package com.shaparapatah.poplibs.ui.userinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.shaparapatah.poplibs.App
import com.shaparapatah.poplibs.databinding.FragmentUserInfoBinding
import com.shaparapatah.poplibs.domain.GithubUsersRepositoryImpl
import com.shaparapatah.poplibs.remote.ApiHolder
import com.shaparapatah.poplibs.remote.connectivity.NetworkStatus
import com.shaparapatah.poplibs.room.AppDataBase
import com.shaparapatah.poplibs.ui.base.BackButtonListener
import com.shaparapatah.poplibs.ui.imageloading.GlideImageLoader
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserInfoFragment : MvpAppCompatFragment(), UserInfoView, BackButtonListener {

    companion object {
        private const val ARG_USER = "ARG_USER_LOGIN"
        fun getNewInstance(userLogin: String): UserInfoFragment {
            return UserInfoFragment().apply { arguments = bundleOf(ARG_USER to userLogin) }
        }
    }

    private val status by lazy { NetworkStatus(requireContext().applicationContext) }

    val presenter: UserInfoPresenter by moxyPresenter {
        UserInfoPresenter(
            userLogin,
            GithubUsersRepositoryImpl(
                networkStatus = status,
                retrofitService = ApiHolder.retrofitService,
                db = AppDataBase.instance
            ),
            App.instance.router
        )
    }

    private val userLogin: String? by lazy {
        arguments?.getString(ARG_USER, "_arg_")
    }
    private var _binding: FragmentUserInfoBinding? = null
    private val binding
        get() = _binding!!

    var adapter: UserInfoAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun showLogin(text: String) {
        binding.tvLogin.text = text
    }

    override fun setImageAvatar(url: String) {
        binding.imageViewAvatar.let {
            GlideImageLoader().loadInto(url, it)
        }
    }

    override fun showTopString(text: String) {
        binding.textViewTopString.text = text
    }

    override fun init() {
        binding.rvUserRepos.layoutManager = LinearLayoutManager(context)
        adapter = UserInfoAdapter(presenter.reposListPresenter)
        binding.rvUserRepos.adapter = adapter

    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
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
