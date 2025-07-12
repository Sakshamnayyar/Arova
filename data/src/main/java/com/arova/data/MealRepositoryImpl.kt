package com.arova.data

import com.arova.domain.MealEntry
import com.arova.domain.MealRepository

class MealRepositoryImpl(private val localMealDao: LocalMealDao) : MealRepository {
    override suspend fun saveMeal(meal: MealEntry) {
        localMealDao.insertMeal(meal)
    }
}
