package com.example.worktest.mvp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment<V : MvpView, P : Presenter<V>> : Fragment() {

    protected lateinit var presenter: P

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = createPresenter()
        presenter.attachView(getMvpView())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
        presenter.onDestroy()
    }

    abstract fun createPresenter(): P

    abstract fun getMvpView(): V
}