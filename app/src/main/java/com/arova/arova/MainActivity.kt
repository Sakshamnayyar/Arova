package com.arova.arova

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arova.arova.ui.theme.ArovaTheme
import com.arova.arova.meallogging.MealViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArovaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MealScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun MealScreen(modifier: Modifier = Modifier, viewModel: MealViewModel = hiltViewModel()) {
    val items by viewModel.items.collectAsState()
    var text by remember { mutableStateOf("") }
    Column(modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(value = text, onValueChange = { text = it }, label = { Text("Meal") })
        Spacer(Modifier.height(8.dp))
        Button(onClick = { viewModel.onLogMealClicked(text) }) {
            Text("Parse")
        }
        LazyColumn {
            items(items) { item ->
                Text(item.name)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArovaTheme {
        Greeting("Android")
    }
}