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

    val typeDeComptePosibility = MutableLiveData<List<String>>()

    val hasLogin : MutableLiveData<Boolean> = MutableLiveData(false)
    val hasRegister : MutableLiveData<Boolean> = MutableLiveData(false)
    lateinit var isRestaurantFormVisible : LiveData<Boolean>

    fun init() {
        isRestaurantFormVisible = typeDeCompte.map { typeDeCompteValue ->
            "Commercant" == typeDeCompteValue.trim()
        }
    }

    fun login() {
        hasLogin.postValue(true)
    }

    fun register() {
        hasRegister.postValue(true)
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

    fun isNomRegisterError() : LiveData<Boolean> {
        return nom.switchMap { nomValue ->
            hasRegister.map { hasRegisterValue ->
                hasRegisterValue && nomValue.trim().isEmpty()
            }
        }
    }

    fun isPrenomRegisterError() : LiveData<Boolean> {
        return prenom.switchMap { prenomValue ->
            hasRegister.map { hasRegisterValue ->
                hasRegisterValue && prenomValue.trim().isEmpty()
            }
        }
    }

    fun isEmailRegisterError() : LiveData<Boolean> {
        return emailRegister.switchMap { emailRegisterValue ->
            hasRegister.map { hasRegisterValue ->
                hasRegisterValue && emailRegisterValue.trim().isEmpty()
            }
        }
    }

    fun isTypeDeCompteRegisterError() : LiveData<Boolean> {
        return typeDeCompte.switchMap { typeDeCompteValue ->
            hasRegister.map { hasRegisterValue ->
                hasRegisterValue && typeDeCompteValue.trim().isEmpty()
            }
        }
    }

    fun isPasswordRegisterError() : LiveData<Boolean> {
        return passwordRegister.switchMap { passwordRegisterValue ->
            hasRegister.map { hasRegisterValue ->
                hasRegisterValue && passwordRegisterValue.trim().isEmpty()
            }
        }
    }

    fun isConfirmPasswordError() : LiveData<Boolean> {
        return confirmPassword.switchMap { confirmPasswordValue ->
            hasRegister.map { hasRegisterValue ->
                hasRegisterValue && confirmPasswordValue.trim().isEmpty()
            }
        }
    }

    fun isNomRestaurantError() : LiveData<Boolean> {
        return nomRestaurant.switchMap { nomRestaurantValue ->
            hasRegister.map { hasRegisterValue ->
                hasRegisterValue && nomRestaurantValue.trim().isEmpty()
            }
        }
    }

    fun isImageError() : LiveData<Boolean> {
        return image.switchMap { imageValue ->
            hasRegister.map { hasRegisterValue ->
                hasRegisterValue && imageValue.trim().isEmpty()
            }
        }
    }

    fun isAdresseError() : LiveData<Boolean> {
        return adresse.switchMap { adresseValue ->
            hasRegister.map { hasRegisterValue ->
                hasRegisterValue && adresseValue.trim().isEmpty()
            }
        }
    }

    fun isVilleError() : LiveData<Boolean> {
        return ville.switchMap { villeValue ->
            hasRegister.map { hasRegisterValue ->
                hasRegisterValue && villeValue.trim().isEmpty()
            }
        }
    }

    fun setTypeDeComptePossibility(typesDeCompte: List<String>) {
        this.typeDeComptePosibility.postValue(typesDeCompte)
    }
}