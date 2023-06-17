package fr.nuroz.quickdiner.fragments

import androidx.lifecycle.*

class LoginViewModel : ViewModel() {

    val email : MutableLiveData<String> = MutableLiveData("")
    val password : MutableLiveData<String> = MutableLiveData("")

    val nom : MutableLiveData<String> = MutableLiveData("")
    val prenom : MutableLiveData<String> = MutableLiveData("")
    val emailRegister : MutableLiveData<String> = MutableLiveData("")
    val typeDeCompte : MutableLiveData<String> = MutableLiveData("")
    val passwordRegister : MutableLiveData<String> = MutableLiveData("")
    val confirmPassword : MutableLiveData<String> = MutableLiveData("")
    val nomRestaurant : MutableLiveData<String> = MutableLiveData("")
    val image : MutableLiveData<String> = MutableLiveData("")
    val adresse : MutableLiveData<String> = MutableLiveData("")
    val ville : MutableLiveData<String> = MutableLiveData("")

    val hasLogin : MutableLiveData<Boolean> = MutableLiveData(false)
    val hasRegister : MutableLiveData<Boolean> = MutableLiveData(false)

    fun login() {
        hasLogin.postValue(true)
    }

    fun register() {

    }

    fun isEmailLoginError() : LiveData<Boolean> {
        return email.switchMap { emailValue ->
            hasLogin.map { hasLoginValue ->
                hasLoginValue && emailValue.trim().isEmpty()
            }
        }
    }

    fun isPasswordLoginError() : LiveData<Boolean> {
        return password.switchMap { passwordValue ->
            hasLogin.map { hasLoginValue ->
                hasLoginValue && passwordValue.trim().isEmpty()
            }
        }
    }
}