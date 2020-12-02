package com.example.worktest.paging

interface PagingView<T> : ProgressMvpView {

    fun setData(data: List<T>)
}
