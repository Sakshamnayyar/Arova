package com.arova.arova.ui.signup

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SignUpScreen(viewModel: SignUpViewModel = hiltViewModel()) {
    Text(text = "Sign Up")
}
