package com.example.booktrackercompose.screens.authorization

import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor() : AuthorizationViewModel() {
    private val mAuth = FirebaseAuth.getInstance()

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
                }
        }
    }
}