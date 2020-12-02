package com.example.worktest.paging

import com.example.worktest.mvp.Presenter

abstract class PagingPresenter<T, V : PagingView<T>> : Presenter<V>() {

    private var isLoading = false
    private var noMoreData = false
    private var currentPage = 1
    protected val data: MutableList<T> = mutableListOf()

    fun onCreate() {
        forceRefresh()
    }

    private fun resetPaging() {
        currentPage = 1
        noMoreData = false
        isLoading = false
        data.clear()
    }

    fun forceRefresh() {
        resetPaging()
        loadNextPage()
    }

    fun loadNextPage() {
        if (isLoading) {
            return
        }

        if (noMoreData) {
            view?.hideProgress()
            return
        }

        loadPage(currentPage)
    }

    protected fun onLoadStarted() {
        view?.showProgress()
        isLoading = true
    }

    protected fun onLoadComplete() {
        view?.hideProgress()
        isLoading = false
    }

    protected fun onNoMoreData() {
        noMoreData = true
    }

    protected fun increasePage(page: Int) {
        currentPage = page + 1
    }

    abstract fun loadPage(page: Int)

}
