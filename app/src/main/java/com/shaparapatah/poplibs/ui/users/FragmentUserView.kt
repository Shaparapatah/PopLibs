package com.shaparapatah.poplibs.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.shaparapatah.poplibs.databinding.FragmentUserViewBinding
import com.shaparapatah.poplibs.model.GithubUserModel
import com.shaparapatah.poplibs.ui.base.BackButtonListener
import moxy.MvpAppCompatFragment

class FragmentUserView : MvpAppCompatFragment(), UsersView, BackButtonListener {

    lateinit var presenter: UsersPresenter

    private var _binding: FragmentUserViewBinding? = null
    private val binding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //binding.textView.setOnClickListener { presenter.onUserClicked() }
        binding.textView.text = tag

    }

    companion object {
        fun getNewInstance(userLogin: String): FragmentUserView {
            return FragmentUserView().apply {
                arguments = bundleOf(
                    "userLogin" to userLogin
                )
            }
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

    override fun updateList(users: List<GithubUserModel>) {
        binding.textView.text = users.toString()
    }

}
