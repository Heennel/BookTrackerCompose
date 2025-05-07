package com.example.booktrackercompose.screens.authorization.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.booktrackercompose.screens.authorization.AuthorizationViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor() : AuthorizationViewModel() {
    private val mAuth = FirebaseAuth.getInstance()

    private val _navigateFlag = mutableStateOf(false)
    val navigateFlag: State<Boolean> = _navigateFlag

    fun dropFlag(){
        _navigateFlag.value = false
    }

    fun updatePassword(value: String) {
        _password.value = value
        _isPasswordValid.value = value.length >= 6 || value.isEmpty()
        updateValidation()
    }

    override fun updateValidation() {
        _isAllValid.value = _isPasswordValid.value
                && _isEmailValid.value
                && _password.value.isNotBlank()
                && _email.value.isNotBlank()
    }

    fun auth() {
        val email = _email.value
        val password = _password.value

        if (email.isNotBlank() && password.isNotBlank()) {
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _toastMessage.value = "Вы вошли в аккаунт"
                    }
                    _navigateFlag.value = true
                }
        }
    }
}