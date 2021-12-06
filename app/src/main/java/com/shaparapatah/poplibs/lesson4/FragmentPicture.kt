package com.shaparapatah.poplibs.lesson4

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shaparapatah.poplibs.databinding.FragmentPictureBinding
import moxy.MvpAppCompatFragment
import java.util.concurrent.Executors

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

        //binding.buttonPicture.setOnClickListener {
        /// TODO: 05.12.2021
        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        var image: Bitmap? = null

        executor.execute {
            val imageURL =
                "https://media.geeksforgeeks.org/wp-content/cdn-uploads/gfg_200x200-min.png"
            try {
                val _in = java.net.URL(imageURL).openStream()
                image = BitmapFactory.decodeStream(_in)
                handler.post {
                    binding.imageView.setImageBitmap(image)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    //}
}


    companion object {
        fun newInstance() = FragmentPicture()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}