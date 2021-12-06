package com.shaparapatah.poplibs.lesson4

import android.os.Bundle
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
}