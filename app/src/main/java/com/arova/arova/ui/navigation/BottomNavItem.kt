package com.arova.arova.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Welcome : BottomNavItem("welcome", Icons.Filled.Star, "Welcome")
    object SignUp : BottomNavItem("signup", Icons.Filled.Person, "Sign Up")
    object Dashboard : BottomNavItem("dashboard", Icons.Filled.Home, "Dashboard")
    object FoodLogging : BottomNavItem("foodlogging", Icons.Filled.List, "Food Logging")
    object UserProfile : BottomNavItem("userprofile", Icons.Filled.Person, "Profile")

    companion object {
        val items = listOf(Welcome,SignUp, Dashboard, FoodLogging, UserProfile)
    }
}
