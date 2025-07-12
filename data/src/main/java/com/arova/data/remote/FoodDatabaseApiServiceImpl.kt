package com.arova.data.remote

import com.arova.data.FoodDatabaseApiService
import com.arova.domain.FoodItem
import javax.inject.Inject

class FoodDatabaseApiServiceImpl @Inject constructor(
    private val service: FoodDatabaseService
) : FoodDatabaseApiService {
    override suspend fun getNutritionInfo(name: String, quantity: Double, unit: String): FoodItem {
        val info = service.getInfo(name, quantity, unit)
        return FoodItem(name, quantity, unit, info.calories, info.protein, info.carbs, info.fat)
    }
}
