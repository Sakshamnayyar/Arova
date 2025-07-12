package com.arova.arova

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arova.arova.ui.navigation.AppNavGraph
import com.arova.arova.ui.theme.ArovaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArovaTheme {
                AppNavGraph()
            }
        }
    }
}
