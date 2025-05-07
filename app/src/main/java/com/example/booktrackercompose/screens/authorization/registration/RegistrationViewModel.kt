package com.example.booktrackercompose.screens.authorization.registration

import androidx.compose.runtime.mutableStateOf
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.runtime.State
import com.example.booktrackercompose.screens.authorization.AuthorizationViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(): AuthorizationViewModel() {

    private val mReg = FirebaseAuth.getInstance()

    private val _confirmPassword = mutableStateOf("")
    val confirmPassword: State<String> = _confirmPassword

    private val _isPasswordsMatch = mutableStateOf(true)
    val isPasswordsMatch: State<Boolean> = _isPasswordsMatch

    private val _navigateFlag = mutableStateOf(false)
    val navigateFlag: State<Boolean> = _navigateFlag

    fun dropFlag(){
        _navigateFlag.value = false
    }

    fun updatePassword(value: String) {
        _password.value = value
        _isPasswordValid.value = value.length >= 6 || value.isEmpty()
        _isPasswordsMatch.value = value == _confirmPassword.value || value.isBlank() || _confirmPassword.value.isBlank()
        updateValidation()
    }

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
                        _navigateFlag.value = true
                    }
                }
        }
    }
}
