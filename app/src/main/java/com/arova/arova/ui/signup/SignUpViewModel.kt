package com.arova.arova.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arova.domain.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    val name = MutableStateFlow("")
    val email = MutableStateFlow("")
    val password = MutableStateFlow("")
    val confirmPassword = MutableStateFlow("")

    val isLoading = MutableStateFlow(false)
    val errorMessage = MutableStateFlow<String?>(null)

    private val _signUpSuccess = MutableSharedFlow<Unit>()
    val signUpSuccess: SharedFlow<Unit> = _signUpSuccess

    fun onSignUpClicked() {
        if (name.value.isBlank() || email.value.isBlank() ||
            password.value.isBlank() || confirmPassword.value.isBlank()
        ) {
            errorMessage.value = "All fields are required"
            return
        }
        if (password.value != confirmPassword.value) {
            errorMessage.value = "Passwords do not match"
            return
        }
        viewModelScope.launch {
            isLoading.value = true
            val result = signUpUseCase(name.value, email.value, password.value)
            isLoading.value = false
            result.onSuccess {
                _signUpSuccess.emit(Unit)
            }.onFailure {
                errorMessage.value = it.message
            }
        }
    }
}
