package com.example.worktest.ui.auth

import com.example.worktest.api.WeatherServiceApi
import com.example.worktest.mvp.Presenter
import com.example.worktest.util.Const
import com.example.worktest.util.isValidEmail
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AuthPresenter(private val api: WeatherServiceApi) : Presenter<AuthView>() {

    private val disposable = CompositeDisposable()

    fun onBtnEnterClicked(email: String, password: String) {
        if (email.isValidEmail()) {
            view?.hideEmailError()
        } else {
            view?.showEmailError()
            return
        }

        if (password.matches(Regex(Const.PASSWORD_REGEX))) {
            view?.hidePasswordError()

        } else {
            view?.showPasswordError()
            return
        }

        getWeather()
    }

    private fun getWeather() {
        view?.hideKeyboard()
        api.getWeather(id = 498817, lang = "ru")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                view?.hideContent()
                view?.showProgress()
            }
            .doAfterTerminate {
                view?.hideProgress()
                view?.showContent()
            }
            .subscribe({
                view?.showSnackBar(
                    city = it.cityName,
                    temperature = it.temperature?.temp,
                    cloudy = it.clouds?.all,
                    humidity = it.temperature?.humidity
                )
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