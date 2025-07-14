package com.arova.data

import com.arova.domain.MealEntry
import com.arova.domain.MealRepository
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(private val localMealDao: LocalMealDao) : MealRepository {
    override suspend fun saveMeal(meal: MealEntry) {
        localMealDao.insertMeal(meal)
    }

    override suspend fun getMealsForDate(date: java.time.LocalDate): List<MealEntry> {
        val start = date.atStartOfDay().toEpochSecond(java.time.ZoneOffset.UTC)
        val end = date.plusDays(1).atStartOfDay().toEpochSecond(java.time.ZoneOffset.UTC) - 1
        return localMealDao.getMealsForDate(start, end).map { entry ->
            MealEntry(
                items = entry.items.map {
                    com.arova.domain.FoodItem(
                        name = it.name,
                        quantity = it.quantity,
                        unit = it.unit,
                        calories = it.calories,
                        protein = it.protein,
                        carbs = it.carbs,
                        fat = it.fat
                    )
                },
                date = java.time.LocalDateTime.ofEpochSecond(entry.meal.date, 0, java.time.ZoneOffset.UTC),
                mealType = entry.meal.mealType
            )
        }
    }
}
