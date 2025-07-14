package com.arova.arova.ui.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

@HiltViewModel
class WelcomeViewModel @Inject constructor() : ViewModel() {

    private val _navigateToSignUp = MutableSharedFlow<Unit>()
    val navigateToSignUp: SharedFlow<Unit> = _navigateToSignUp

    fun onSignUpClicked() {
        viewModelScope.launch { _navigateToSignUp.emit(Unit) }
    }
}
