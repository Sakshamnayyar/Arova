package com.arova.data

import com.arova.data.local.MealEntryWithItems
import com.arova.domain.MealEntry

interface LocalMealDao {
    suspend fun insertMeal(meal: MealEntry)

    suspend fun getMealsForDate(start: Long, end: Long): List<MealEntryWithItems>
}
