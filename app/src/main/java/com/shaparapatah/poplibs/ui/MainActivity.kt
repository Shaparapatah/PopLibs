package com.shaparapatah.poplibs.ui

import android.os.Bundle
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



        binding.btnCounter0.setOnClickListener {
            presenter.onClicked0()
        }
        binding.btnCounter1.setOnClickListener {
            presenter.onClicked1()
        }
        binding.btnCounter2.setOnClickListener {
            presenter.onClicked2()
        }
    }

    override fun setButtonText0(text: String) {
        binding.btnCounter0.text = text
    }

    override fun setButtonText1(text: String) {
        binding.btnCounter1.text = text
    }

    override fun setButtonText2(text: String) {
        binding.btnCounter2.text = text
    }
}


