package com.shaparapatah.poplibs.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.shaparapatah.poplibs.databinding.ActivityMainBinding
import com.shaparapatah.poplibs.presenter.MainPresenter

class MainActivity : AppCompatActivity(), MainView {

    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding!!

    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listener = View.OnClickListener {
            presenter.btnClick()

        }

        binding.btnCounter1.setOnClickListener(listener)
        binding.btnCounter2.setOnClickListener(listener)
        binding.btnCounter3.setOnClickListener(listener)
    }

    override fun setButtonText(text: String) {
        binding.btnCounter1.text = text
        binding.btnCounter2.text = text
        binding.btnCounter3.text = text

    }

}


