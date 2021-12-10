package com.shaparapatah.poplibs.lesson4

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.shaparapatah.poplibs.App
import com.shaparapatah.poplibs.databinding.FragmentPictureBinding
import com.shaparapatah.poplibs.model.ConvertJpgToPng
import com.shaparapatah.poplibs.ui.base.BackButtonListener
import com.shaparapatah.poplibs.ui.base.MySchedulersFactory
import com.shaparapatah.poplibs.ui.main.ImageConverterView
import moxy.MvpAppCompatActivity
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class FragmentPicture : MvpAppCompatFragment(), ImageConverterView, BackButtonListener {

    private var imageUri: Uri? = null
    private var _binding: FragmentPictureBinding? = null
    private val binding
        get() = _binding!!
    private val presenter: ConverterPresenter by moxyPresenter {
        ConverterPresenter(
            ConvertJpgToPng(requireContext()),
            MySchedulersFactory.create(),
            App.instance.router
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 111 && resultCode == Activity.RESULT_OK) {
            imageUri = data?.data
            imageUri?.let { presenter.showOriginalImage(it) }
        }
    }

    override fun backPressed(): Boolean = presenter.backPressed()


    override fun init() {
        binding.buttonPicture.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/jpg"
            startActivityForResult(intent, 111)
        }

        binding.startConvertingBtn.setOnClickListener {
            imageUri?.let {
                (presenter::startConvertingImage)
            }
        }
    }

    override fun showOriginalImage(uri: Uri) {
        binding.imageView.setImageURI(uri)
    }

    override fun showConvertedImage(uri: Uri) {
        binding.convertedImage.setImageURI(uri)
    }

    companion object {
        fun newInstance() = FragmentPicture()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}