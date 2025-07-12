package com.arova.arova.ui.foodlogging

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FoodLoggingScreen(viewModel: FoodLoggingViewModel = hiltViewModel()) {
    Text(text = "Food Logging")
}
