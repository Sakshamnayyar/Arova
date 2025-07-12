package com.arova.domain

class SaveMealEntryUseCase(private val repository: MealRepository) {
    suspend operator fun invoke(meal: MealEntry) {
        repository.saveMeal(meal)
    }
}
