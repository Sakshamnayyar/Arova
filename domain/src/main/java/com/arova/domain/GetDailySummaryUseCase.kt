package com.arova.domain

import java.time.LocalDate
import javax.inject.Inject

class GetDailySummaryUseCase @Inject constructor(private val repository: MealRepository) {
    suspend operator fun invoke(date: LocalDate): DailySummary {
        val meals = repository.getMealsForDate(date)
        val calories = meals.sumOf { meal -> meal.items.sumOf { it.calories } }
        val protein = meals.sumOf { meal -> meal.items.sumOf { it.protein } }
        val carbs = meals.sumOf { meal -> meal.items.sumOf { it.carbs } }
        val fat = meals.sumOf { meal -> meal.items.sumOf { it.fat } }
        return DailySummary(calories, protein, carbs, fat)
    }
}
