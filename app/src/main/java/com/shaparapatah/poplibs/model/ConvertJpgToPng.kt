package com.shaparapatah.poplibs.model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.core.net.toUri
import io.reactivex.rxjava3.core.Single
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.TimeUnit

class ConvertJpgToPng(val currentContext: Context) {
    fun convertRx(uri: Uri?): Single<Uri> {
        uri?.let {
            val tempConvertedFile = File.createTempFile("tmpConvert", ".png")
            val outStream = FileOutputStream(tempConvertedFile)
            val bufOutStream = BufferedOutputStream(outStream)
            val checkVersion = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                ImageDecoder.decodeBitmap(
                    ImageDecoder.createSource(currentContext.contentResolver, it)
                )
            } else {
                MediaStore.Images.Media.getBitmap(currentContext.contentResolver, it)
            }

            checkVersion.compress(Bitmap.CompressFormat.PNG, 100, bufOutStream)
            bufOutStream.close()
            outStream.close()
            return Single.just(tempConvertedFile.toUri()).delay(3L, TimeUnit.SECONDS)
        }
        return Single.error(Throwable())
    }
}