package com.shaparapatah.poplibs.lesson4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shaparapatah.poplibs.databinding.FramgentViewBinding
import moxy.MvpAppCompatFragment

class FragmentView : MvpAppCompatFragment() {

    private var _binding: FramgentViewBinding? = null
    private val binding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FramgentViewBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.buttonView.setOnClickListener { 
            /// TODO: 05.12.2021  
        }
    }
    
    
    
    

    companion object {
        fun newInstance() = FragmentView()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}