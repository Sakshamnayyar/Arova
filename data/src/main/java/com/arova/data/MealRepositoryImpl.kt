package com.arova.data

import com.arova.domain.MealEntry
import com.arova.domain.MealRepository
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(private val localMealDao: LocalMealDao) : MealRepository {
    override suspend fun saveMeal(meal: MealEntry) {
        localMealDao.insertMeal(meal)
    }
}
