package com.arova.arova.ui.UserProfile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun UserProfile(){
    Box(modifier = Modifier.fillMaxSize()) {
        Text("User Profile",
            fontSize =48.sp,
            modifier = Modifier.align(Alignment.Center))
    }

}