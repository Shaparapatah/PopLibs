package com.shaparapatah.poplibs.lesson4

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.*
import android.os.Environment.DIRECTORY_DOWNLOADS
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import com.shaparapatah.poplibs.databinding.FragmentPictureBinding
import moxy.MvpAppCompatFragment
import java.io.File
import java.net.URL
import java.util.concurrent.Executors
import java.util.jar.Manifest

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
        files()

        binding.buttonPicture.let {
            it.setOnClickListener {
                val intent = Intent()
                    .setType("*/*")
                    .setAction(Intent.ACTION_GET_CONTENT)
                activity?.let { fragmentActivity ->
                    fragmentActivity.startActivityForResult(
                        Intent.createChooser(intent, "Выберите файл"), 111
                    )
                }
            }

        }
    }

    fun isStoragePermissionGranted(): Boolean {
        return if (requireActivity().checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_GRANTED
        ) {
            Log.v("mylogs", "Permission is granted")
            true
        } else {
            Log.v("mylogs", "Permission is revoked")
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                1
            )
            false
        }
    }


    fun files() {

        val path: String =
            Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS).absolutePath
        Log.d("mylogs", "Path: $path")
        val directory = File(path)
        val files = directory.listFiles()
        files?.let {
            Log.d("mylogs", "Size: " + it.size)
            for (i in it.indices) {
                Log.d("mylogs", "FileName:" + it[i].name)
            }
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