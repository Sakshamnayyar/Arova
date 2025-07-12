package com.arova.domain

import javax.inject.Inject

class SaveMealEntryUseCase @Inject constructor(private val repository: MealRepository) {
    suspend operator fun invoke(meal: MealEntry) {
        repository.saveMeal(meal)
    }
}
