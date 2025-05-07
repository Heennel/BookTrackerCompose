package com.example.booktrackercompose.screens.authorization

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class AuthorizationViewModel: ViewModel() {

    protected val _email = mutableStateOf("")
    val email: State<String> = _email

    protected val _password = mutableStateOf("")
    val password: State<String> = _password

    protected val _isEmailValid = mutableStateOf(true)
    val isEmailValid: State<Boolean> = _isEmailValid

    protected val _isPasswordValid = mutableStateOf(true)
    val isPasswordValid: State<Boolean> = _isPasswordValid

    protected val _toastMessage = MutableLiveData<String?>()
    val toastMessage: LiveData<String?> get() = _toastMessage

    protected val _isAllValid = mutableStateOf(false)
    val isAllValid: State<Boolean> = _isAllValid

    fun updateEmail(value: String) {
        _email.value = value
        _isEmailValid.value = isValidEmail(value) || value.isEmpty()
        updateValidation()
    }

    protected abstract fun updateValidation()

    protected fun isValidEmail(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())
    }

}