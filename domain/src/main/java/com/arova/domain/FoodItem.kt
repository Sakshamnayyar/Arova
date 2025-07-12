package com.arova.domain

data class FoodItem(
    val name: String,
    val quantity: Double,
    val unit: String,
    val calories: Int,
    val protein: Double,
    val carbs: Double,
    val fat: Double
)
