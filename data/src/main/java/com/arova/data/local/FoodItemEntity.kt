package com.arova.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_items")
data class FoodItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val mealId: Long,
    val name: String,
    val quantity: Double,
    val unit: String,
    val calories: Int,
    val protein: Double,
    val carbs: Double,
    val fat: Double
)
