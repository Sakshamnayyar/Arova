package com.arova.data.local

import com.arova.data.LocalMealDao
import com.arova.domain.MealEntry
import java.time.ZoneOffset

class RoomLocalMealDao(private val dao: MealDao) : LocalMealDao {
    override suspend fun insertMeal(meal: MealEntry) {
        val mealId = dao.insertMeal(
            MealEntryEntity(
                date = meal.date.toEpochSecond(ZoneOffset.UTC),
                mealType = meal.mealType
            )
        )
        val items = meal.items.map {
            FoodItemEntity(
                mealId = mealId,
                name = it.name,
                quantity = it.quantity,
                unit = it.unit,
                calories = it.calories,
                protein = it.protein,
                carbs = it.carbs,
                fat = it.fat
            )
        }
        dao.insertItems(items)
    }

    override suspend fun getMealsForDate(start: Long, end: Long): List<MealEntryWithItems> {
        return dao.getMealsForDate(start, end)
    }
}
