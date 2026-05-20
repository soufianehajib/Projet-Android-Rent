package com.example.rent.ui.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    fun onIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.EmailChanged -> _state.update { it.copy(email = intent.email, error = null) }
            is LoginIntent.PasswordChanged -> _state.update { it.copy(password = intent.password, error = null) }
            LoginIntent.Submit -> performLogin()
        }
    }

    private fun performLogin() {
        val current = _state.value
        _state.update { it.copy(isLoading = true) }

        // Basic validation check
        if (current.email.contains("@") && current.password.length >= 6) {
            _state.update { it.copy(isLoading = false, isSuccess = true) }
        } else {
            _state.update {
                it.copy(
                    isLoading = false,
                    error = "Invalid email or password must be at least 6 characters"
                )
            }
        }
    }
}