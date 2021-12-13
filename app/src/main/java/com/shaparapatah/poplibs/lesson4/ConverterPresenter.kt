package com.shaparapatah.poplibs.lesson4

import android.net.Uri
import com.github.terrakok.cicerone.Router
import com.shaparapatah.poplibs.model.ConvertJpgToPng
import com.shaparapatah.poplibs.ui.base.IMySchedulers
import com.shaparapatah.poplibs.ui.main.ImageConverterView
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class ConverterPresenter(
    private val converter: ConvertJpgToPng,
    private val schedulers: IMySchedulers,
    val router: Router,

    ) : MvpPresenter<ImageConverterView>() {


    var disposables = CompositeDisposable()

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }


    fun startConvertingImage(imageUri: Uri) {
        converter
            .convertRx(imageUri)
            .subscribeOn(schedulers.computation())
            .observeOn(schedulers.main())
            .subscribe(
                object : SingleObserver<Uri> {
                    override fun onSubscribe(d: Disposable?) {
                        disposables.add(d)
                    }

                    override fun onSuccess(t: Uri?) {
                        if (t != null) {
                            convertImageSuccess(t)
                        }
                    }

                    override fun onError(e: Throwable) {
                        Throwable(e)
                    }

                }
            )
    }

    fun convertImageSuccess(uri: Uri) {
        viewState.showConvertedImage(uri)
    }

    fun showOriginalImage(imageUri: Uri) {
        viewState.showOriginalImage(imageUri)
    }

}