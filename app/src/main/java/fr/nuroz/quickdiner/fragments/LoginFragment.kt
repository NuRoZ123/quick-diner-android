package fr.nuroz.quickdiner.fragments

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.viewModelScope
import com.google.android.material.textfield.TextInputLayout
import fr.nuroz.quickdiner.R
import fr.nuroz.quickdiner.databinding.FragmentLoginBinding
import fr.nuroz.quickdiner.retrofit.UserController
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment() {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding
    private lateinit var userController: UserController

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentLoginBinding.inflate(inflater, container, false)

        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        loginViewModel.init()
        binding.loginViewModel = loginViewModel

        binding.loginButton.setOnClickListener {
            loginViewModel.login()
        }

        binding.registerButton.setOnClickListener {
            loginViewModel.register()
        }

        userController = retrofit.create(UserController::class.java)

        loginViewModel.viewModelScope.launch {
            val response = userController.getTypes()
            if (response.isSuccessful) {
                loginViewModel.setTypeDeComptePossibility(response.body()!!)
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        initErrorFields()
        initVisibilityRestaurantInfo()
        initTypeDeCompte()
    }

    fun initErrorFields() {
        initErrorField(loginViewModel.isEmailLoginError(), binding.emailLoginTextLayout, getString(R.string.email_required))
        initErrorField(loginViewModel.isPasswordLoginError(), binding.passwordLoginTextLayout, getString(R.string.password_required))
        initErrorField(loginViewModel.isNomRegisterError(), binding.lastNameRegisterTextLayout, getString(R.string.nom_required))
        initErrorField(loginViewModel.isPrenomRegisterError(), binding.firstNameRegisterTextLayout, getString(R.string.prenom_required))
        initErrorField(loginViewModel.isEmailRegisterError(), binding.emailRegisterTextLayout, getString(R.string.email_required))
        initErrorField(loginViewModel.isTypeDeCompteRegisterError(), binding.typeDeCompteRegisterTextLayout, getString(R.string.type_de_compte_required))
        initErrorField(loginViewModel.isPasswordRegisterError(), binding.passwordRegisterTextLayout, getString(R.string.password_required))
        initErrorField(loginViewModel.isConfirmPasswordError(), binding.confirmPasswordRegisterTextLayout, getString(R.string.confirm_password_required))
        initErrorField(loginViewModel.isNomRestaurantError(), binding.resteauNameRegisterTextLayout, getString(R.string.nom_restaurant_required))
        initErrorField(loginViewModel.isImageError(), binding.imageRegisterTextLayout, getString(R.string.image_required))
        initErrorField(loginViewModel.isAdresseError(), binding.resteauAdresseRegisterTextLayout, getString(R.string.adresse_required))
        initErrorField(loginViewModel.isVilleError(), binding.resteauVilleRegisterTextLayout, getString(R.string.ville_required))
    }

    fun initErrorField(LiveData: LiveData<Boolean>, TextInputLayout: TextInputLayout, errorText: String) {
        addObservers(LiveData)
        LiveData.distinctUntilChanged().observe(this) { hasError ->
            TextInputLayout.isErrorEnabled = hasError
            if(hasError) {
                TextInputLayout.error = errorText
            }
        }
    }

    fun initVisibilityRestaurantInfo() {
        addObservers(loginViewModel.isRestaurantFormVisible)
        loginViewModel.isRestaurantFormVisible.observe(this) {
            val visibility = if (it) View.VISIBLE else View.GONE

            binding.infoRestauRegisterText.visibility = visibility
            binding.resteauNameRegisterTextLayout.visibility = visibility
            binding.imageRegisterTextLayout.visibility = visibility
            binding.resteauAdresseRegisterTextLayout.visibility = visibility
            binding.resteauVilleRegisterTextLayout.visibility = visibility
        }
    }
    fun initTypeDeCompte() {
        addObservers(loginViewModel.typeDeComptePosibility)
        loginViewModel.typeDeComptePosibility.observe(this) { typeDeComptePosibility ->
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, typeDeComptePosibility)

            binding.typeDeCompteRegisterAutoCompleteTextView.setAdapter(adapter)
        }

        binding.typeDeCompteRegisterAutoCompleteTextView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                loginViewModel.typeDeCompte.postValue(s.toString())
            }
        })
    }

}