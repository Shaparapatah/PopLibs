package com.shaparapatah.poplibs.lesson4

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.shaparapatah.poplibs.R
import com.shaparapatah.poplibs.databinding.ActivityViewBinding
import moxy.MvpAppCompatActivity

class ViewActivity : MvpAppCompatActivity() {

    lateinit var binding: ActivityViewBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, FragmentPicture.newInstance()).commit()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 111 && resultCode == RESULT_OK) {
            val selectedFile: Uri? = data?.data //The uri with the location of the file
            Toast.makeText(this, "${selectedFile}", Toast.LENGTH_SHORT).show()
            Log.d("mylogs", "${selectedFile}")
        }
    }
}