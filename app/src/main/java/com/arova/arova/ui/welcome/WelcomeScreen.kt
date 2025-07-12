package com.arova.arova.ui.welcome

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun WelcomeScreen(viewModel: WelcomeViewModel = hiltViewModel()) {
    Text(text = "Welcome")
}
