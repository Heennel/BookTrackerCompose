package com.example.booktrackercompose.screens.authorization

import androidx.compose.runtime.mutableStateOf
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.runtime.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(): AuthorizationViewModel() {

    private val mReg = FirebaseAuth.getInstance()

    private val _confirmPassword = mutableStateOf("")
    val confirmPassword: State<String> = _confirmPassword

    private val _isPasswordsMatch = mutableStateOf(true)
    val isPasswordsMatch: State<Boolean> = _isPasswordsMatch

    fun updateConfirmPassword(value: String) {
        _confirmPassword.value = value
        _isPasswordsMatch.value = value.isEmpty() || _password.value == value
        updateValidation()
    }

    override fun updateValidation() {
        _isAllValid.value = _isPasswordValid.value
                && _isPasswordsMatch.value
                && _isEmailValid.value
                && _password.value.isNotBlank()
                && _email.value.isNotBlank()
                && _confirmPassword.value.isNotBlank()
    }

    fun sign() {
        val email = _email.value
        val password = _password.value
        if (email.isNotBlank() && password.isNotBlank()) {
            mReg.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _toastMessage.value = "Регистрация успешна"
                    }
                }
        }
    }
}
