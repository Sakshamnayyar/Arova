package com.arova.domain

interface MealRepository {
    suspend fun saveMeal(meal: MealEntry)

    suspend fun getMealsForDate(date: java.time.LocalDate): List<MealEntry>
}
