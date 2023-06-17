package fr.nuroz.quickdiner.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.distinctUntilChanged
import fr.nuroz.quickdiner.R
import fr.nuroz.quickdiner.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment() {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentLoginBinding.inflate(inflater, container, false)

        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.loginViewModel = loginViewModel

        binding.loginButton.setOnClickListener {
            loginViewModel.login()
        }

        binding.registerButton.setOnClickListener {
            loginViewModel.register()
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        initErrorVisibility()
    }

    fun initErrorVisibility() {

        val emailLoginError = loginViewModel.isEmailLoginError()
        addObservers(emailLoginError)
        emailLoginError.observe(this) { hasError ->
            binding.emailLoginTextLayout.isErrorEnabled = hasError
            if(hasError) {
                binding.emailLoginTextLayout.error = getString(R.string.email_required)
            }
            println(hasError)
        }

        val passwordLoginError = loginViewModel.isPasswordLoginError()
        addObservers(passwordLoginError)
        passwordLoginError.observe(this) { hasError ->
            binding.passwordLoginTextLayout.isErrorEnabled = hasError
            if(hasError) {
                binding.passwordLoginTextLayout.error = getString(R.string.password_required)
            }
            println(hasError)
        }


    }
}