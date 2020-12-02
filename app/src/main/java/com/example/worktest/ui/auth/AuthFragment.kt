package com.example.worktest.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.worktest.R
import com.example.worktest.databinding.FragmentAuthBinding
import com.example.worktest.mvp.BaseFragment
import com.example.worktest.ui.MainActivity
import com.example.worktest.util.gone
import com.example.worktest.util.visible
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.get

class AuthFragment : BaseFragment<AuthView, AuthPresenter>(), AuthView {

    private lateinit var binding: FragmentAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnEnter.setOnClickListener {
            presenter.onBtnEnterClicked(
                email = binding.email.text.toString(),
                password = binding.password.text.toString()
            )
        }
    }

    override fun showSnackBar(
        city: String?,
        temperature: Double?,
        cloudy: Double?,
        humidity: Double?
    ) {
        Snackbar.make(
            binding.rootView,
            "Город=$city температура=$temperature облачность=$cloudy влажность=$humidity",
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun hideKeyboard() {
        val host = activity
        if (host is MainActivity) {
            host.hideKeyboard(activity!!)
        }
    }

    override fun showEmailError() {
        binding.emailLayout.error = getString(R.string.incorrect_email)
    }

    override fun hideEmailError() {
        binding.emailLayout.error = null
    }

    override fun showPasswordError() {
        binding.passwordLayout.error = getString(R.string.incorrect_password)
    }

    override fun hidePasswordError() {
        binding.passwordLayout.error = null
    }

    override fun hideContent() {
        binding.views.gone()
    }

    override fun showContent() {
        binding.views.visible()
    }

    override fun showProgress() {
        binding.progressBar.visible()
    }

    override fun hideProgress() {
        binding.progressBar.gone()
    }

    override fun getMvpView(): AuthView = this

    override fun createPresenter(): AuthPresenter = get()
}