package com.shaparapatah.poplibs.lesson4

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.shaparapatah.poplibs.App
import com.shaparapatah.poplibs.databinding.FragmentPictureBinding
import com.shaparapatah.poplibs.model.ConvertJpgToPng
import com.shaparapatah.poplibs.ui.base.BackButtonListener
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isStoragePermissionGranted()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 111 && resultCode == MvpAppCompatActivity.RESULT_OK) {
            imageUri = data?.data
            imageUri?.let {
                presenter.originalImage(it)
            }
        }
    }

    override fun backPressed(): Boolean = presenter.backPressed()


    private fun isStoragePermissionGranted(): Boolean {
        return if (requireActivity().checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_GRANTED
        ) {
            true
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                1
            )
            false
        }
    }

    companion object {
        fun newInstance() = FragmentPicture()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun init() {
        binding.buttonPicture.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/jpg"
            startActivityForResult(intent, 111)
        }
    }

    override fun showOriginImage(uri: Uri) {
        TODO("Not yet implemented")
    }

    override fun showConvertedImage(uri: Uri) {
        TODO("Not yet implemented")
    }

}