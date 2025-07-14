package com.arova.arova.ui.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DashboardScreen(viewModel: DashboardViewModel = hiltViewModel()) {
    val date by viewModel.currentDate.collectAsState()
    val summary by viewModel.summary.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            IconButton(onClick = { viewModel.onPreviousDayClicked() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "prev")
            }
            Text(text = date.toString(), modifier = Modifier.weight(1f))
            IconButton(onClick = { viewModel.onNextDayClicked() }) {
                Icon(Icons.Default.ArrowForward, contentDescription = "next")
            }
        }
        Spacer(Modifier.height(16.dp))
        Text(text = "Calories: ${summary.totalCalories}")
        Text(text = "Protein: ${summary.protein}")
        Text(text = "Carbs: ${summary.carbs}")
        Text(text = "Fat: ${summary.fat}")
    }
}
