package com.example.worktest.paging

import com.example.worktest.mvp.MvpView

interface ProgressMvpView : MvpView {

    fun showProgress()

    fun hideProgress()
}
