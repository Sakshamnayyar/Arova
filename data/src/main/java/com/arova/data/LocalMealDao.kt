package com.arova.data

import com.arova.domain.MealEntry

interface LocalMealDao {
    suspend fun insertMeal(meal: MealEntry)
}
