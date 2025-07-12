package com.arova.domain

interface MealRepository {
    suspend fun saveMeal(meal: MealEntry)
}
