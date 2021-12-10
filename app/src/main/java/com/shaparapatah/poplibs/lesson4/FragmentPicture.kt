package com.shaparapatah.poplibs.lesson4

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.os.Environment.DIRECTORY_DCIM
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.shaparapatah.poplibs.databinding.FragmentPictureBinding
import moxy.MvpAppCompatFragment
import java.io.File

class FragmentPicture : MvpAppCompatFragment() {


    private var _binding: FragmentPictureBinding? = null
    private val binding
        get() = _binding!!


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

        binding.buttonPicture.let {
            it.setOnClickListener {
                val intent = Intent()
                    .setType("*/*")
                    .setAction(Intent.ACTION_GET_CONTENT)
                activity?.let { ActivityView ->
                    ActivityView.startActivityForResult(
                        Intent.createChooser(intent, "Выберите файл"), 111
                    )

                }

            }

        }
    }

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

}