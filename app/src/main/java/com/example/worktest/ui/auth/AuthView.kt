package com.example.worktest.ui.auth

import com.example.worktest.paging.ProgressMvpView

interface AuthView : ProgressMvpView {

    fun showEmailError()

    fun hideEmailError()

    fun showPasswordError()

    fun hidePasswordError()

    fun hideContent()

    fun showContent()

    fun hideKeyboard()

    fun showSnackBar(city: String?, temperature: Double?, cloudy: Double?, humidity: Double?)
}