package com.example.worktest.di

import com.example.worktest.ui.auth.AuthPresenter
import com.example.worktest.ui.images.ImagesPresenter
import org.koin.dsl.module.module

val presenterModule = module {

    factory { ImagesPresenter(get()) }

    factory { AuthPresenter(get()) }
}