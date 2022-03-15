package com.shaparapatah.poplibs.ui.imageloading

import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideImageLoader : ImageLoader<ImageView> {
    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container)
            .asBitmap()
            .load(url)
            .circleCrop()
            .into(container)
    }
}