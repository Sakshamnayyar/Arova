package com.arova.data

import com.arova.domain.MealEntry

interface LocalMealDao {
    fun insertMeal(meal: MealEntry)
}
