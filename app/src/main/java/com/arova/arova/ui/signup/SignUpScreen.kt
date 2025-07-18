package com.arova.arova.ui.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arova.arova.R
import com.arova.arova.ui.common.CustomTextField
import com.arova.arova.ui.common.PrimaryButton

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    onSignedUp: () -> Unit = {}
) {
    val firstName by viewModel.firstName.collectAsState()
    val lastName by viewModel.lastName.collectAsState()
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val confirm by viewModel.confirmPassword.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.signUpSuccess.collect { onSignedUp() }
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.signup_screen_bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Create your Account",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.W600
            )
            Spacer(Modifier.size(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                CustomTextField(
                    modifier = Modifier.weight(1f),
                    value = firstName,
                    label = "First Name",
                    onValueChange = { viewModel.firstName.value = it }
                )
                Spacer(Modifier.size(8.dp))
                CustomTextField(
                    modifier = Modifier.weight(1f),
                    value = lastName,
                    label = "Last Name",
                    onValueChange = { viewModel.lastName.value = it }
                )
            }
            Spacer(Modifier.size(8.dp))
            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                label = "Email",
                onValueChange = { viewModel.email.value = it })
            Spacer(Modifier.size(8.dp))
            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = { viewModel.password.value = it },
                label = "Password",
                isPassword = true
            )
            Spacer(Modifier.size(8.dp))
            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                value = confirm,
                onValueChange = { viewModel.confirmPassword.value = it },
                label = "Confirm Password",
                isPassword = true
            )
            Spacer(Modifier.size(16.dp))
            if (isLoading) {
                CircularProgressIndicator()
            } else {
                PrimaryButton(
                    text = "Sign Up",
                    onClick = { viewModel.onSignUpClicked() },
                    backgroundColor = Color.Black,
                    contentColor = Color.White
                )
            }
            Spacer(Modifier.size(8.dp))
            Text(
                text = "Already have an account? Log In",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.W400
            )
            error?.let {
                Text(text = it, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SignUpScreenPreview() {
    SignUpScreen()
}
