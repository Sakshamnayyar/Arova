package com.arova.data

import com.arova.domain.FoodItem

interface FoodDatabaseApiService {
    suspend fun getNutritionInfo(name: String, quantity: Double, unit: String): FoodItem
}
