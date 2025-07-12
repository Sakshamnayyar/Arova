package com.arova.domain

class ParseMealUseCase(private val repository: FoodRepository) {
    suspend operator fun invoke(query: String): List<FoodItem> {
        return repository.parseFood(query)
    }
}
