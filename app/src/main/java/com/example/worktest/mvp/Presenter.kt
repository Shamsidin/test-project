package com.example.worktest.mvp

import java.lang.ref.WeakReference

open class Presenter<V : MvpView> {
    private var viewRef: WeakReference<V>? = null

    val view: V?
        get() {
            return viewRef?.get()
        }

    fun attachView(view: V) {
        viewRef = WeakReference(view)
    }

    fun detachView() {
        viewRef?.clear()
        viewRef = null
    }

    open fun onDestroy() {
    }
}