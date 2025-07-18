package com.arova.arova.ui.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.arova.arova.ui.common.PrimaryButton

@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel = hiltViewModel(),
    onSignUp: () -> Unit = {}
) {
    LaunchedEffect(Unit) {
        viewModel.navigateToSignUp.collect { onSignUp() }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.welcome_screen_bg),
                contentDescription = "Welcome to Arova!",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()

            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Welcome to Arova!",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.W600
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Track your health and wellness journey",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.W400
            )
            Text(
                text = "Log your calories and macros effortlessly",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.W400
            )

        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PrimaryButton(
                text = "Sign Up",
                onClick = { viewModel.onSignUpClicked() },
                backgroundColor = Color.Black,
                contentColor = Color.White
            )
            Spacer(Modifier.height(8.dp))
            PrimaryButton(
                text = "Login",
                onClick = { viewModel.onSignInClicked() },
                backgroundColor = Color(0xFFDBDBD6),
                contentColor = Color.Black
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun WelcomeScreenPreview() {
    WelcomeScreen(onSignUp = {})
}
