package com.arova.data

import com.arova.domain.FoodItem
import com.arova.domain.FoodRepository

class FoodRepositoryImpl(
    private val geminiApi: GeminiApiService,
    private val foodDatabaseApi: FoodDatabaseApiService
) : FoodRepository {
    override suspend fun parseFood(query: String): List<FoodItem> {
        val names = geminiApi.parseNaturalLanguage(query)
        return names.map { name ->
            foodDatabaseApi.getNutritionInfo(name, 1.0, "unit")
        }
    }
}
