package com.example.worktest.ui.images

import com.example.worktest.api.ImageServiceApi
import com.example.worktest.model.Image
import com.example.worktest.paging.PagingPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class ImagesPresenter(private val apiImage: ImageServiceApi) :
    PagingPresenter<Image, ImagesView>() {

    private val disposable = CompositeDisposable()

    fun onViewCreated() {
        super.onCreate()
    }

    override fun loadPage(page: Int) {
        apiImage.getImages(page, 10)
            .delay(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onLoadStarted() }
            .doAfterTerminate { onLoadComplete() }
            .subscribe({
                if (it.isEmpty()) {
                    onNoMoreData()
                } else {
                    increasePage(page)
                    data.addAll(it)
                }
                view?.setData(data)
            }, {
                it.printStackTrace()
            })
            .also { disposable.add(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}