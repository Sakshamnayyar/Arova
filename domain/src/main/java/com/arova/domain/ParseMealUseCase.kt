package com.arova.domain

import javax.inject.Inject

class ParseMealUseCase @Inject constructor(private val repository: FoodRepository) {
    suspend operator fun invoke(query: String): List<FoodItem> {
        return repository.parseFood(query)
    }
}
