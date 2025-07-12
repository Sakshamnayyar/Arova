package com.arova.domain

import java.time.LocalDateTime

data class MealEntry(
    val items: List<FoodItem>,
    val date: LocalDateTime,
    val mealType: String
)
