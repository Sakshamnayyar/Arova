package com.arova.domain

interface FoodRepository {
    suspend fun parseFood(query: String): List<FoodItem>
}
