package com.arova.arova.ui.foodlogging

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FoodLoggingScreen(
    viewModel: FoodLoggingViewModel = hiltViewModel(),
    onSaved: () -> Unit = {}
) {
    val input by viewModel.userInput.collectAsState()
    val items by viewModel.parsedFoodItems.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.errorMessage.collectAsState()

    androidx.compose.runtime.LaunchedEffect(Unit) {
        viewModel.mealSaved.collect { onSaved() }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = input,
            onValueChange = { viewModel.userInput.value = it },
            label = { Text("Describe your meal") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        Button(onClick = { viewModel.onAnalyzeClicked() }) { Text("Analyze") }
        Spacer(Modifier.height(8.dp))
        if (isLoading) {
            CircularProgressIndicator()
        }
        items.forEach {
            Text(text = "${'$'}{it.quantity} ${'$'}{it.unit} ${'$'}{it.name} - ${'$'}{it.calories} cal")
        }
        if (items.isNotEmpty()) {
            Spacer(Modifier.height(8.dp))
            Button(onClick = { viewModel.onConfirmAndSaveClicked("Meal") }) { Text("Save Meal") }
        }
        error?.let {
            Spacer(Modifier.height(8.dp))
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }
    }
}
