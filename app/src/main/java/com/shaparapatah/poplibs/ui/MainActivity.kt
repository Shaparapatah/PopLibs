package com.shaparapatah.poplibs.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shaparapatah.poplibs.databinding.ActivityMainBinding
import com.shaparapatah.poplibs.model.CountersModel
import com.shaparapatah.poplibs.presenter.MainPresenter
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding!!

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter {
        return MainPresenter(CountersModel())
    }

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


