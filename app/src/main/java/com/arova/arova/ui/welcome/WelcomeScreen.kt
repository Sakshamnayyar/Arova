package com.arova.arova.ui.welcome

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel = hiltViewModel(),
    onSignUp: () -> Unit = {}
) {
    androidx.compose.runtime.LaunchedEffect(Unit) {
        viewModel.navigateToSignUp.collect { onSignUp() }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to Arova")
        Spacer(Modifier.height(16.dp))
        Button(onClick = { viewModel.onSignUpClicked() }) { Text("Sign Up") }
    }
}
