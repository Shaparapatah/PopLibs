package com.shaparapatah.poplibs.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shaparapatah.poplibs.databinding.FragmentUserViewBinding

class FragmentUserView : Fragment() {


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

}
