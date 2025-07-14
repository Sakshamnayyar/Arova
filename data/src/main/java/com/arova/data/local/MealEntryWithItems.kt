package com.arova.data.local

import androidx.room.Embedded
import androidx.room.Relation

data class MealEntryWithItems(
    @Embedded val meal: MealEntryEntity,
    @Relation(parentColumn = "id", entityColumn = "mealId")
    val items: List<FoodItemEntity>
)
